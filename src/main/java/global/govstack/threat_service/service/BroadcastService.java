package global.govstack.threat_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.threat_service.controller.exception.InternalServerException;
import global.govstack.threat_service.controller.exception.NotFoundException;
import global.govstack.threat_service.dto.broadcast.*;
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
    private final UserService userService;
    private final ObjectMapper mapper;
    private static final String EMPTY_STRING = "";

    public boolean canBroadcast(UUID userId, int countryId) {
        return this.userService.canBroadcast(userId, countryId);
    }

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
        broadcast.setTitle(EMPTY_STRING);
        broadcast.setStatus(BroadcastStatus.DRAFT);
        broadcast.setCountryId(1L);
        broadcast.setCountryName("Kenya");
        broadcast.setPrimaryLangMessage(EMPTY_STRING);
        broadcast.setSecondaryLangMessage(EMPTY_STRING);
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

    public BroadcastDto publishBroadcast(UUID broadcastId, UUID userId) {
        final BroadcastDto broadcastDto = validateBeforePublish(broadcastId, userId);
        final KafkaBroadcastDto kafkaBroadcastDto = new KafkaBroadcastDto(
                broadcastId,
                broadcastDto.title(),
                broadcastDto.channelType(),
                broadcastDto.periodStart(),
                broadcastDto.periodEnd(),
                broadcastDto.primaryLangMessage(),
                broadcastDto.secondaryLangMessage(),
                broadcastDto.countryId(),
                broadcastDto.affectedCounties().stream().map(CreateBroadcastCountyDto::countyId).toList(),
                userId
        );
        try {
            this.imPublisher.publishBroadcast(this.mapper.writeValueAsString(kafkaBroadcastDto));
            if (!this.userService.checkUser(userId)) {
                this.imPublisher.publishServiceLogging(this.mapper.writeValueAsString(LogInfoDto.builder()
                        .from("Threat Service")
                        .to("Information Mediator BB")
                        .content("Broadcast sent to Information Mediator")
                        .timeStamp(LocalDateTime.now())
                        .broadcastId(broadcastDto.broadcastId().toString())
                        .build()));
            }
            return this.updateBroadcast(broadcastId, broadcastDto, BroadcastStatus.PROCESSING);
        } catch (JsonProcessingException e) {
            throw new InternalServerException("Something went wrong with publishing message: " + e);
        }
    }

    private BroadcastDto validateBeforePublish(UUID broadcastId, UUID userId) {
        final Broadcast broadcast = this.broadcastRepository.findBroadcastByBroadcastUUID(broadcastId)
                .orElseThrow(() -> new NotFoundException("Broadcast with id " + broadcastId + " not found"));
        if (canBroadcast(userId, broadcast.getCountryId().intValue())) {
            return this.broadcastMapper.entityToDto(broadcast);
        }
        throw new NotFoundException("User doesn't have publish permission");
    }

    public void delete(UUID broadcastId) {
        final Broadcast broadcast = broadcastRepository.findBroadcastByBroadcastUUID(broadcastId)
                .orElseThrow(() -> new NotFoundException("Broadcast with id " + broadcastId + " not found"));
        if (broadcast.getStatus().equals(BroadcastStatus.DRAFT)) {
            broadcastRepository.delete(broadcast);
        } else {
            throw new InternalServerException("Cannot delete a broadcast that is not in draft status");
        }
    }
}
