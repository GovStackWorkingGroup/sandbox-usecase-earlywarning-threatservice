package global.govstack.weather_event_service.service;

import global.govstack.weather_event_service.mapper.BroadcastMapper;
import global.govstack.weather_event_service.dto.BroadcastDto;
import global.govstack.weather_event_service.pub_sub.IMPublisher;
import global.govstack.weather_event_service.repository.BroadcastRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class BroadcastService {

    private final BroadcastRepository broadcastRepository;
    private final BroadcastMapper broadcastMapper;
    private final UserService userService;
    private final IMPublisher imPublisher;

    public BroadcastService(
            BroadcastRepository broadcastRepository, BroadcastMapper broadcastMapper, UserService userService, IMPublisher imPublisher) {
        this.broadcastRepository = broadcastRepository;
        this.broadcastMapper = broadcastMapper;
        this.userService = userService;
        this.imPublisher = imPublisher;
    }

    public List<BroadcastDto> getAllBroadcasts() {
        return this.broadcastRepository.findAll().stream()
                .map(this.broadcastMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public Optional<BroadcastDto> getBroadcastById(UUID broadcastUUID) {
        return this.broadcastRepository.findBroadcastByBroadcastUUID(broadcastUUID).map(this.broadcastMapper::entityToDto);
    }

    public void saveBroadcast(UUID userUUID, BroadcastDto broadcastDto) {
//    boolean canBroadcast = userService.canBroadcast(userUUID);
//    if (!canBroadcast) {
//      log.error("Forbidden to broadcast");
//      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden to broadcast");
//    }
        var savedBroadcast = broadcastRepository.save(this.broadcastMapper.dtoToEntity(broadcastDto));
        var savedBroadcastFullDto = this.broadcastMapper.entityToDto(savedBroadcast);
//    todo potentially could cause transaction rollback issue
        this.imPublisher.publishBroadcast(savedBroadcastFullDto);
    }
}
