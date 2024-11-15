package global.govstack.threat_service.service;

import global.govstack.threat_service.controller.exception.InternalServerException;
import global.govstack.threat_service.controller.exception.NotFoundException;
import global.govstack.threat_service.dto.broadcast.BroadcastDto;
import global.govstack.threat_service.dto.broadcast.CreateBroadcastCountyDto;
import global.govstack.threat_service.dto.broadcast.KafkaBroadcastDto;
import global.govstack.threat_service.dto.broadcast.ThreatIdDto;
import global.govstack.threat_service.mapper.BroadcastMapper;
import global.govstack.threat_service.pub_sub.IMPublisher;
import global.govstack.threat_service.repository.BroadcastRepository;
import global.govstack.threat_service.repository.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BroadcastService {

    private final BroadcastRepository broadcastRepository;
    private final BroadcastMapper broadcastMapper;
    private final IMPublisher imPublisher;
    private final ThreatService threatService;

    public Page<BroadcastDto> getAllBroadcasts(String country, boolean active, UUID userId, BroadcastStatus status, Pageable pageable) {
        return broadcastRepository.findAll(country, active, userId, status, pageable).map(broadcastMapper::entityToDto);
    }

    public Optional<BroadcastDto> getBroadcastById(UUID broadcastId) {
        return broadcastRepository.findBroadcastByBroadcastUUID(broadcastId).map(broadcastMapper::entityToDto);
    }

    public BroadcastDto saveBroadcast(ThreatIdDto threatIdDto) {
        final ThreatEvent threat = threatService.getThreatEntityById(threatIdDto.threatId())
                .orElseThrow(() -> new NotFoundException("Threat with id " + threatIdDto.threatId() + " not found"));
        final Broadcast broadcast = createDraftBroadcast(threat);
        final List<BroadcastCounty> affectedCounties = getAffectedCounties(threat, broadcast);
        broadcast.setAffectedCounties(affectedCounties);
        final Broadcast savedBroadcast = broadcastRepository.save(broadcast);
        return broadcastMapper.entityToDto(savedBroadcast);
    }

    private Broadcast createDraftBroadcast(ThreatEvent threat) {
        final Broadcast broadcast = new Broadcast();
        broadcast.setThreatEvent(threat);
        broadcast.setTitle(BroadcastStatus.DRAFT.toString());
        broadcast.setStatus(BroadcastStatus.DRAFT);
        broadcast.setCountryId(1L);
        broadcast.setCountryName("Kenya");
        broadcast.setPrimaryLangMessage(BroadcastStatus.DRAFT.toString());
        broadcast.setSecondaryLangMessage(BroadcastStatus.DRAFT.toString());
        broadcast.setPeriodStart(threat.getPeriodStart());
        broadcast.setPeriodEnd(threat.getPeriodEnd());
        broadcast.setCreatedAt(LocalDateTime.now());
        broadcast.setCreatedBy(UUID.fromString("5a2e626c-1222-44a3-b5a2-8cb21167c354"));
        return broadcast;
    }

    private List<BroadcastCounty> getAffectedCounties(ThreatEvent threat, Broadcast broadcast) {
        return threat.getAffectedCountries().stream()
                .filter(countryThreat -> "Kenya".equals(countryThreat.getCountryName()))
                .flatMap(countryThreat -> countryThreat.getAffectedCounties().stream().map(countyCountry -> {
                    BroadcastCounty broadcastCounty = new BroadcastCounty();
                    broadcastCounty.setCountyId(countyCountry.getCountyId());
                    broadcastCounty.setCountyName(countyCountry.getCountyName());
                    broadcastCounty.setBroadcast(broadcast);
                    return broadcastCounty;
                })).toList();
    }

    public BroadcastDto updateBroadcast(UUID broadcastId, BroadcastDto broadcastDto, BroadcastStatus status) {
        final Optional<Broadcast> existingBroadcast = this.broadcastRepository.findBroadcastByBroadcastUUID(broadcastId);
        if (existingBroadcast.isPresent()) {
            Broadcast updatedBroadcast = existingBroadcast.get();
            updatedBroadcast.setChannelType(ChannelType.valueOf(broadcastDto.channelType()).name());
            updatedBroadcast.setTitle(broadcastDto.title());
            updatedBroadcast.setStatus(status);
            updatedBroadcast.setCountryId(broadcastDto.countryId());
            updatedBroadcast.setCountryName(broadcastDto.countryName());
            updatedBroadcast.setPrimaryLangMessage(broadcastDto.primaryLangMessage());
            updatedBroadcast.setSecondaryLangMessage(broadcastDto.secondaryLangMessage());
            if (status.equals(BroadcastStatus.PROCESSING)) {
                updatedBroadcast.setInitiated(LocalDateTime.now());
            }

            this.broadcastRepository.save(updatedBroadcast);
            return this.broadcastMapper.entityToDto(updatedBroadcast);
        }
        throw new InternalServerException("No broadcast found for ID: " + broadcastId);
    }

    public BroadcastDto publishBroadcast(UUID broadcastId, BroadcastDto broadcastDto) {
        if(broadcastDto.primaryLangMessage().isBlank() && broadcastDto.secondaryLangMessage().isBlank()){
            throw new InternalServerException("Broadcast has to have at least one message to be published");
        }
        final KafkaBroadcastDto kafkaBroadcastDto = new KafkaBroadcastDto(
                broadcastId,
                broadcastDto.title(),
                broadcastDto.channelType(),
                broadcastDto.periodStart(),
                broadcastDto.periodEnd(),
                broadcastDto.primaryLangMessage(),
                broadcastDto.secondaryLangMessage(),
                broadcastDto.countryId(),
                broadcastDto.affectedCounties().stream().map(CreateBroadcastCountyDto::countyId).toList()
        );
        this.imPublisher.publishBroadcast(kafkaBroadcastDto);
        return this.updateBroadcast(broadcastId, broadcastDto, BroadcastStatus.PROCESSING);
    }
}
