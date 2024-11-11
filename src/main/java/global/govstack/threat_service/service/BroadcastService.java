package global.govstack.threat_service.service;

import global.govstack.threat_service.controller.exception.NotFoundException;
import global.govstack.threat_service.dto.broadcast.BroadcastCreateDto;
import global.govstack.threat_service.dto.broadcast.BroadcastDto;
import global.govstack.threat_service.dto.broadcast.CreateBroadcastCountyDto;
import global.govstack.threat_service.dto.broadcast.KafkaBroadcastDto;
import global.govstack.threat_service.mapper.BroadcastMapper;
import global.govstack.threat_service.pub_sub.IMPublisher;
import global.govstack.threat_service.repository.BroadcastRepository;
import global.govstack.threat_service.repository.entity.Broadcast;
import global.govstack.threat_service.repository.entity.BroadcastStatus;
import global.govstack.threat_service.repository.entity.ThreatEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public BroadcastDto saveBroadcast(BroadcastCreateDto broadcastDto) {
        return saveOrUpdateBroadcast(broadcastMapper.createDtoToEntity(broadcastDto), BroadcastStatus.DRAFT, broadcastDto.threatId());
    }

    public BroadcastDto updateBroadcast(BroadcastDto broadcastDto, BroadcastStatus broadcastStatus) {
        return saveOrUpdateBroadcast(broadcastMapper.dtoToEntity(broadcastDto), broadcastStatus, broadcastDto.threatId());
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

    public BroadcastDto publishBroadcast(BroadcastDto broadcastDto) {
        final KafkaBroadcastDto kafkaBroadcastDto = new KafkaBroadcastDto(
                broadcastDto.broadcastId(),
                broadcastDto.title(),
                broadcastDto.periodStart(),
                broadcastDto.periodEnd(),
                broadcastDto.primaryLangMessage(),
                broadcastDto.secondaryLangMessage(),
                broadcastDto.countryId(),
                broadcastDto.affectedCounties().stream().map(CreateBroadcastCountyDto::countyId).toList()
        );
        this.imPublisher.publishBroadcast(kafkaBroadcastDto);
        return this.updateBroadcast(broadcastDto, BroadcastStatus.PUBLISHED);
    }
}
