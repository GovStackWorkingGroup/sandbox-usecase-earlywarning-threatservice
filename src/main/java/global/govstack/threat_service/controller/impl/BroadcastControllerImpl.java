package global.govstack.threat_service.controller.impl;

import global.govstack.threat_service.controller.BroadcastControllerInterface;
import global.govstack.threat_service.controller.exception.NotFoundException;
import global.govstack.threat_service.dto.broadcast.BroadcastDto;
import global.govstack.threat_service.dto.broadcast.ThreatIdDto;
import global.govstack.threat_service.repository.entity.BroadcastStatus;
import global.govstack.threat_service.service.BroadcastService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/broadcasts")
public class BroadcastControllerImpl implements BroadcastControllerInterface {

    private final BroadcastService broadcastService;

    @Override
    public Page<BroadcastDto> getAllBroadcasts(String country, boolean active, UUID userId, BroadcastStatus status, Pageable pageable) {
        return broadcastService.getAllBroadcasts(country, active, userId, status, pageable);
    }

    @Override
    public BroadcastDto getBroadcastDetails(UUID broadcastId) {
        return broadcastService.getBroadcastById(broadcastId)
                .orElseThrow(() -> new NotFoundException("Broadcast with ID " + broadcastId + " not found"));
    }

    @Override
    public BroadcastDto createBroadcast(ThreatIdDto threatIdDto) {
        return broadcastService.saveBroadcast(threatIdDto);
    }

    @Override
    public BroadcastDto updateBroadcast(UUID broadcastId, BroadcastDto broadcastDto) {
        return broadcastService.updateBroadcast(broadcastId, broadcastDto, broadcastDto.status());
    }

    @Override
    public ResponseEntity<?> userCanBroadcast(UUID userId, int countryId) {
        return ResponseEntity.ok(this.broadcastService.canBroadcast(userId, countryId));
    }

    @Override
    public BroadcastDto publishBroadcast(UUID broadcastId, UUID userId) {
        return this.broadcastService.publishBroadcast(broadcastId, userId);
    }

    @Override
    public void deleteBroadcast(UUID broadcastId) {
        broadcastService.delete(broadcastId);
    }
}
