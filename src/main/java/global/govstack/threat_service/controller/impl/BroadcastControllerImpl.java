package global.govstack.threat_service.controller.impl;

import global.govstack.threat_service.controller.BroadcastControllerInterface;
import global.govstack.threat_service.controller.exception.NotFoundException;
import global.govstack.threat_service.controller.exception.UnauthorizedException;
import global.govstack.threat_service.dto.broadcast.BroadcastCreateDto;
import global.govstack.threat_service.dto.broadcast.BroadcastDto;
import global.govstack.threat_service.repository.entity.BroadcastStatus;
import global.govstack.threat_service.service.BroadcastService;
import global.govstack.threat_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/broadcasts")
public class BroadcastControllerImpl implements BroadcastControllerInterface {

    private final BroadcastService broadcastService;
    private final UserService userService;

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
    public BroadcastDto saveBroadcast(BroadcastCreateDto broadcastDto) {
        return broadcastService.saveBroadcast(broadcastDto);
    }

    @Override
    public BroadcastDto updateBroadcast(UUID broadcastId, UUID userId, BroadcastDto broadcastDto) {
        return broadcastService.updateBroadcast(broadcastDto, broadcastDto.status());
    }

    @Override
    public ResponseEntity<Boolean> userCanBroadcast(UUID userId, int countryId) {
        return this.userService.canBroadcast(userId, countryId) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Override
    public BroadcastDto publishBroadcast(UUID broadcastId, UUID userId, BroadcastDto broadcastDto) {
        if (this.userService.canBroadcast(userId, broadcastDto.countryId().intValue())) {
            return this.broadcastService.publishBroadcast(broadcastDto);
        }
        throw new UnauthorizedException("User doesn't have publish permission");
    }
}
