package global.govstack.weather_event_service.controller.impl;

import global.govstack.weather_event_service.controller.BroadcastControllerInterface;
import global.govstack.weather_event_service.dto.BroadcastDto;
import global.govstack.weather_event_service.service.BroadcastService;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/broadcast/")
public class BroadcastControllerImpl implements BroadcastControllerInterface {

    private final BroadcastService broadcastService;

    public BroadcastControllerImpl(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @Override
    public List<BroadcastDto> getAllBroadcasts() {
        return this.broadcastService.getAllBroadcasts();
    }

    @Override
    public BroadcastDto getBroadcastDetails(UUID broadcastUUID) {
        return this.broadcastService.getBroadcastById(broadcastUUID).orElse(null);
    }

    @Override
    public void saveBroadcast(UUID userUUID, BroadcastDto broadcastDto) {
        this.broadcastService.saveBroadcast(userUUID, broadcastDto);
    }
}
