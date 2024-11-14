package global.govstack.threat_service.controller;

import global.govstack.threat_service.dto.broadcast.BroadcastDto;
import global.govstack.threat_service.dto.broadcast.ThreatIdDto;
import global.govstack.threat_service.repository.entity.BroadcastStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface BroadcastControllerInterface {

    @GetMapping()
    Page<BroadcastDto> getAllBroadcasts(
            @RequestParam(required = false) String country,
            @RequestParam(required = false, defaultValue = "false") boolean active,
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) BroadcastStatus status,
            Pageable pageable);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    BroadcastDto createBroadcast(@RequestBody ThreatIdDto broadcastDto);

    @GetMapping("/{broadcastId}")
    BroadcastDto getBroadcastDetails(@PathVariable("broadcastId") UUID broadcastId);

    @PutMapping(path = "/{broadcastId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    BroadcastDto updateBroadcast(
            @PathVariable("broadcastId") UUID broadcastId,
            @RequestParam UUID userId,
            @RequestBody BroadcastDto broadcastDto
    );

    @GetMapping(path = "/canBroadcast")
    ResponseEntity<?> userCanBroadcast(@RequestParam("userId") UUID userId, @RequestParam("countryId") int countryId);

    @PostMapping(path = "/publishBroadcast")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<BroadcastDto> publishBroadcast(
            @RequestParam UUID userId,
            @RequestBody BroadcastDto broadcastDto);
}