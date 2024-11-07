package global.govstack.threat_service.service;

import global.govstack.threat_service.dto.BroadcastCreateDto;
import global.govstack.threat_service.dto.BroadcastDto;
import global.govstack.threat_service.dto.KafkaBroadcastDto;
import global.govstack.threat_service.mapper.BroadcastMapper;
import global.govstack.threat_service.pub_sub.IMPublisher;
import global.govstack.threat_service.repository.BroadcastRepository;
import global.govstack.threat_service.repository.entity.*;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BroadcastService {

    private final BroadcastRepository broadcastRepository;
    private final BroadcastMapper broadcastMapper;
    private final UserService userService;
    private final IMPublisher imPublisher;
    private final ThreatService threatService;

    public Page<BroadcastDto> getAllBroadcasts(Pageable pageable) {
        return broadcastRepository.findAll(pageable).map(broadcastMapper::entityToDto);
    }

    public Optional<BroadcastDto> getBroadcastByUuid(UUID broadcastUUID) {
        return broadcastRepository.findBroadcastByBroadcastUUID(broadcastUUID).map(broadcastMapper::entityToDto);
    }

    public BroadcastDto saveBroadcast(UUID userUUID, BroadcastCreateDto broadcastDto) {
        return saveOrUpdateBroadcast(userUUID, broadcastMapper.createDtoToEntity(broadcastDto), BroadcastStatus.DRAFT, broadcastDto.threatId());
    }

    public BroadcastDto updateBroadcast(UUID userUUID, BroadcastDto broadcastDto) {
        return saveOrUpdateBroadcast(userUUID, broadcastMapper.dtoToEntity(broadcastDto), broadcastDto.status(), broadcastDto.threatId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BroadcastDto saveOrUpdateBroadcast(UUID userUUID, Broadcast broadcast, BroadcastStatus status, long threatId) {
        if (userService.canBroadcast(userUUID)) {
            final ThreatEvent threatEvent = threatService.getThreatById(threatId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Threat not found"));
            broadcast.setThreatEvent(threatEvent);
            broadcast.setStatus(status);
            if (status.equals(BroadcastStatus.PUBLISHED)) {
                broadcast.setInitiated(LocalDateTime.now());
            }
            broadcast.getAffectedCounties().forEach(county -> {
                county.setBroadcast(broadcast);
            });
            return this.broadcastMapper.entityToDto(broadcastRepository.save(broadcast));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Broadcast not allowed");
        }
    }

    //TODO method should do only one thing, so publish process should be moved to a separate method
    public BroadcastDto updateAndPublish(UUID broadcastUUID, UUID userUUID, BroadcastDto broadcastDto) {
        // FIXME bringing unused broadcastUUID from controller to service, is it really not needed?

        final BroadcastDto savedBroadcast = updateBroadcast(userUUID, broadcastDto);
        if (savedBroadcast.status().equals(BroadcastStatus.PUBLISHED)) {
            final var threatById = threatService.getThreatById(broadcastDto.threatId()).get(); // FIXME what if threat is not found?
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
            imPublisher.publishBroadcast(kafkaBroadcastDto);
        }
        return savedBroadcast;
    }
}
