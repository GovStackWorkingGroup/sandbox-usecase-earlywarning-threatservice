package global.govstack.weather_event_service.service;

import global.govstack.weather_event_service.dto.BroadcastCreateDto;
import global.govstack.weather_event_service.dto.BroadcastDto;
import global.govstack.weather_event_service.mapper.BroadcastMapper;
import global.govstack.weather_event_service.pub_sub.IMPublisher;
import global.govstack.weather_event_service.repository.BroadcastRepository;
import global.govstack.weather_event_service.repository.entity.Broadcast;
import global.govstack.weather_event_service.repository.entity.ThreatEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static global.govstack.weather_event_service.repository.entity.EventStatus.DRAFT;

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
        boolean canBroadcast = userService.canBroadcast(userUUID);
        if (!canBroadcast) {
            log.error("Forbidden to broadcast");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden to broadcast");
        }
        final Broadcast broadcast = this.broadcastMapper.createDtoToEntity(broadcastDto);
        final ThreatEvent threatEvent = threatService.getThreatById(broadcastDto.threatId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Threat not found"));
        broadcast.setThreatEvent(threatEvent);
        broadcast.setStatus(DRAFT);

        final Broadcast savedBroadcast = broadcastRepository.save(broadcast);
        final BroadcastDto savedBroadcastFullDto = this.broadcastMapper.entityToDto(savedBroadcast);
        this.imPublisher.publishBroadcast(savedBroadcastFullDto);
        return savedBroadcastFullDto;
    }
}
