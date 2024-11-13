package global.govstack.threat_service.service;

import global.govstack.threat_service.controller.exception.NotFoundException;
import global.govstack.threat_service.dto.broadcast.BroadcastDto;
import global.govstack.threat_service.dto.broadcast.CreateBroadcastCountyDto;
import global.govstack.threat_service.dto.broadcast.KafkaBroadcastDto;
import global.govstack.threat_service.dto.broadcast.ThreatUuidDto;
import global.govstack.threat_service.mapper.BroadcastMapper;
import global.govstack.threat_service.pub_sub.IMPublisher;
import global.govstack.threat_service.repository.BroadcastRepository;
import global.govstack.threat_service.repository.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    public UUID saveBroadcast(ThreatUuidDto broadcastDto) {
        final ThreatEvent threat = threatService.getThreatEntityById(broadcastDto.threatUUID())
                .orElseThrow(() -> new NotFoundException("Threat with id " + broadcastDto.threatUUID() + " not found"));
        final Broadcast broadcast = createDraftBroadcast(threat);
        final List<BroadcastCounty> affectedCounties = getAffectedCounties(threat, broadcast);
        broadcast.setAffectedCounties(affectedCounties);
        broadcastRepository.save(broadcast);
        return broadcast.getBroadcastUUID();
    }

    private Broadcast createDraftBroadcast(ThreatEvent threat) {
        final Broadcast broadcast = new Broadcast();
        broadcast.setThreatEvent(threat);
        broadcast.setTitle(BroadcastStatus.DRAFT.toString());
        broadcast.setStatus(BroadcastStatus.DRAFT);
        broadcast.setChannelType(ChannelType.DRAFT);
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

    public BroadcastDto updateBroadcast(BroadcastDto broadcastDto) {
        return saveOrUpdateBroadcast(broadcastMapper.dtoToEntity(broadcastDto), broadcastDto.status(), broadcastDto.threatId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BroadcastDto saveOrUpdateBroadcast(Broadcast broadcast, BroadcastStatus status, UUID threatId) {
        final ThreatEvent threatEvent = threatService.getThreatEntityById(threatId)
                .orElseThrow(() -> new NotFoundException("Threat with id " + threatId + " not found"));
        broadcast.setThreatEvent(threatEvent);
        broadcast.setStatus(status);
        if (status.equals(BroadcastStatus.PUBLISHED)) {
            broadcast.setInitiated(LocalDateTime.now());
        }
        broadcast.getAffectedCounties().forEach(county -> county.setBroadcast(broadcast));
        return this.broadcastMapper.entityToDto(broadcastRepository.save(broadcast));
    }

    //TODO publish should be separated funtionality
    public BroadcastDto updateAndPublish(BroadcastDto broadcastDto) {
        // TODO EVERYONE use broadcastId from controller instead from the DTO
        final BroadcastDto savedBroadcast = updateBroadcast(broadcastDto);
        if (savedBroadcast.status().equals(BroadcastStatus.PUBLISHED)) {
            mapAndPublish(broadcastDto);
        }
        return savedBroadcast;
    }

    private void mapAndPublish(BroadcastDto broadcastDto) {
        final KafkaBroadcastDto kafkaBroadcastDto = new KafkaBroadcastDto(
                broadcastDto.broadcastId(),
                broadcastDto.title(),
                broadcastDto.channelType(),
                broadcastDto.periodStart(),
                broadcastDto.periodEnd(),
                broadcastDto.primaryLangMessage(),
                broadcastDto.secondaryLangMessage(),
                broadcastDto.countryId(),
                broadcastDto.affectedCounties().stream().map(CreateBroadcastCountyDto::countyId).toList()
        );
        imPublisher.publishBroadcast(kafkaBroadcastDto);
    }
}
