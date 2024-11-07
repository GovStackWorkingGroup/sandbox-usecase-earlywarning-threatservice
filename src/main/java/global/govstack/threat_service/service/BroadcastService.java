package global.govstack.threat_service.service;

import global.govstack.threat_service.dto.BroadcastCreateDto;
import global.govstack.threat_service.dto.BroadcastDto;
import global.govstack.threat_service.dto.KafkaBroadcastDto;
import global.govstack.threat_service.mapper.BroadcastMapper;
import global.govstack.threat_service.pub_sub.IMPublisher;
import global.govstack.threat_service.repository.BroadcastRepository;
import global.govstack.threat_service.repository.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@Transactional
public class BroadcastService {

    private final BroadcastRepository broadcastRepository;
    private final BroadcastMapper broadcastMapper;
    private final UserService userService;
    private final IMPublisher imPublisher;
    private final ThreatService threatService;

    public BroadcastService(
            BroadcastRepository broadcastRepository, BroadcastMapper broadcastMapper, UserService userService, IMPublisher imPublisher, ThreatService threatService) {
        this.broadcastRepository = broadcastRepository;
        this.broadcastMapper = broadcastMapper;
        this.userService = userService;
        this.imPublisher = imPublisher;
        this.threatService = threatService;
    }

    public Page<BroadcastDto> getAllBroadcasts(Pageable pageable) {
        return this.broadcastRepository.findAll(pageable).map(this.broadcastMapper::entityToDto);
    }

    public Optional<BroadcastDto> getBroadcastById(UUID broadcastUUID) {
        return this.broadcastRepository.findBroadcastByBroadcastUUID(broadcastUUID).map(this.broadcastMapper::entityToDto);
    }

    public BroadcastDto saveBroadcast(UUID userUUID, BroadcastCreateDto broadcastDto) {
        return saveOrUpdateBroadcast(userUUID, this.broadcastMapper.createDtoToEntity(broadcastDto), BroadcastStatus.DRAFT, broadcastDto.threatId());
    }

    public BroadcastDto updateBroadcast(UUID userUUID, BroadcastDto broadcastDto) {
        return saveOrUpdateBroadcast(userUUID, this.broadcastMapper.dtoToEntity(broadcastDto), broadcastDto.status(), broadcastDto.threatId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BroadcastDto saveOrUpdateBroadcast(UUID userUUID, Broadcast broadcast, BroadcastStatus status, long threatId) {
        if (this.userService.canBroadcast(userUUID)) {
            final ThreatEvent threatEvent = threatService.getThreatById(threatId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Threat not found"));
            broadcast.setThreatEvent(threatEvent);
            broadcast.setStatus(status);
            if (status.equals(BroadcastStatus.PUBLISHED)) {
                broadcast.setInitiated(LocalDateTime.now());
            }
            final Broadcast savedBroadcast = broadcastRepository.save(broadcast);
            return this.broadcastMapper.entityToDto(savedBroadcast);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Broadcast not allowed");
        }
    }

    //TODO method should do only one thing, so publish process should be moved to a separate method
    public BroadcastDto updateAndPublish(UUID userUUID, BroadcastDto broadcastDto) {
        final BroadcastDto savedBroadcast = this.updateBroadcast(userUUID, broadcastDto);
        if (savedBroadcast.status().equals(BroadcastStatus.PUBLISHED)) {
            final var threatById = threatService.getThreatById(broadcastDto.threatId()).get();
            List<Long> countryIds = threatById.getAffectedCountries().stream().map(CountryThreat::getCountryId).toList();
            List<Long> countyIds = threatById.getAffectedCountries().stream()
                    .flatMap(item -> item.getAffectedCounties().stream())
                    .map(CountyCountry::getCountyId)
                    .toList();
            final KafkaBroadcastDto kafkaBroadcastDto = new KafkaBroadcastDto(
                    broadcastDto.broadcastUUID(),
                    broadcastDto.title(),
                    threatById.getPeriodStart(),
                    threatById.getPeriodEnd(),
                    broadcastDto.primaryLangMessage(),
                    broadcastDto.secondaryLangMessage(),
                    countryIds,
                    countyIds
            );
            this.imPublisher.publishBroadcast(kafkaBroadcastDto);
        }
        return savedBroadcast;
    }
}
