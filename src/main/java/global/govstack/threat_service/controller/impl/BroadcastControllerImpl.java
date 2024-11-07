package global.govstack.threat_service.controller.impl;

import global.govstack.threat_service.controller.BroadcastControllerInterface;
import global.govstack.threat_service.controller.exception.NotFoundException;
import global.govstack.threat_service.dto.BroadcastCreateDto;
import global.govstack.threat_service.dto.BroadcastDto;
import global.govstack.threat_service.service.BroadcastService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/broadcasts")
public class BroadcastControllerImpl implements BroadcastControllerInterface {

    private final BroadcastService broadcastService;

    @Override
    public Page<BroadcastDto> getAllBroadcasts(Pageable pageable) {
        return broadcastService.getAllBroadcasts(pageable);
    }

    @Override
    public BroadcastDto getBroadcastDetails(UUID broadcastUUID) {
        return broadcastService.getBroadcastByUuid(broadcastUUID)
            .orElseThrow(() -> new NotFoundException("Broadcast with UUID " + broadcastUUID + " not found"));
    }

    @Override
    public BroadcastDto saveBroadcast(UUID userUUID, BroadcastCreateDto broadcastDto) {
       return broadcastService.saveBroadcast(userUUID, broadcastDto);
    }

    @Override
    public BroadcastDto updateBroadcast(UUID broadcastUUID, UUID userUUID, BroadcastDto broadcastDto) {
       return broadcastService.updateAndPublish(broadcastUUID, userUUID, broadcastDto);
    }
}
