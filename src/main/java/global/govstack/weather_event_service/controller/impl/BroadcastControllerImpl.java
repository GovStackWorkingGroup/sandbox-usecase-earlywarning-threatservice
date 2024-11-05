package global.govstack.weather_event_service.controller.impl;

import global.govstack.weather_event_service.controller.BroadcastControllerInterface;
import global.govstack.weather_event_service.dto.BroadcastCreateDto;
import global.govstack.weather_event_service.dto.BroadcastDto;
import global.govstack.weather_event_service.service.BroadcastService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/broadcast/")
public class BroadcastControllerImpl implements BroadcastControllerInterface {

    private final BroadcastService broadcastService;

    public BroadcastControllerImpl(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @Override
    public Page<BroadcastDto> getAllBroadcasts(Pageable pageable) {
        return this.broadcastService.getAllBroadcasts(pageable);
    }

    @Override
    public BroadcastDto getBroadcastDetails(UUID broadcastUUID) {
        return this.broadcastService.getBroadcastById(broadcastUUID).orElse(null);
    }

    @Override
    public void saveBroadcast(UUID userUUID, BroadcastCreateDto broadcastDto) {
        this.broadcastService.saveBroadcast(userUUID, broadcastDto);
    }
}
