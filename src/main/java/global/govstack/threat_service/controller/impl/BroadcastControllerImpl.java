package global.govstack.threat_service.controller.impl;

import global.govstack.threat_service.controller.BroadcastControllerInterface;
import global.govstack.threat_service.controller.exception.NotFoundException;
import global.govstack.threat_service.dto.broadcast.BroadcastCreateDto;
import global.govstack.threat_service.dto.broadcast.BroadcastDto;
import global.govstack.threat_service.service.BroadcastService;
import global.govstack.threat_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/broadcasts")
public class BroadcastControllerImpl implements BroadcastControllerInterface {

    private final BroadcastService broadcastService;
    private final UserService userService;

    @Override
    public Page<BroadcastDto> getAllBroadcasts(String country, boolean active, UUID userUUID, Pageable pageable) {
        return broadcastService.getAllBroadcasts(country, active, userUUID, pageable);
    }

    @Override
    public BroadcastDto getBroadcastDetails(UUID broadcastId) {
        return broadcastService.getBroadcastById(broadcastId)
                .orElseThrow(() -> new NotFoundException("Broadcast with ID " + broadcastId + " not found"));
    }

    @Override
    public BroadcastDto saveBroadcast(BroadcastCreateDto broadcastDto) {
        return broadcastService.saveBroadcast(broadcastDto);
    }

    @Override
    public BroadcastDto updateBroadcast(UUID broadcastId, UUID userId, BroadcastDto broadcastDto) {
        if (!userService.canBroadcast(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Broadcast not allowed");
        }
        return broadcastService.updateAndPublish(broadcastDto);
    }
}
