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
    public BroadcastDto getBroadcastDetails(UUID broadcastId) {
        return broadcastService.getBroadcastById(broadcastId)
            .orElseThrow(() -> new NotFoundException("Broadcast with ID " + broadcastId + " not found"));
    }

    @Override
    public BroadcastDto saveBroadcast(UUID userId, BroadcastCreateDto broadcastDto) {
       return broadcastService.saveBroadcast(userId, broadcastDto);
    }

    @Override
    public BroadcastDto updateBroadcast(UUID broadcastId, UUID userId, BroadcastDto broadcastDto) {
       return broadcastService.updateAndPublish(broadcastId, userId, broadcastDto);
    }
}
