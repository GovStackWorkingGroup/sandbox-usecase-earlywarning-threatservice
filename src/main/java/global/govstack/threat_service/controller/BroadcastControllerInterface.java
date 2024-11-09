package global.govstack.threat_service.controller;

import global.govstack.threat_service.dto.broadcast.BroadcastCreateDto;
import global.govstack.threat_service.dto.broadcast.BroadcastDto;
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
            Pageable pageable);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    BroadcastDto saveBroadcast(@RequestBody BroadcastCreateDto broadcastDto);

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

    @GetMapping(path = "/{userId}")
    ResponseEntity<Boolean> userCanBroadcast(@PathVariable("userId")UUID userId);

    @PostMapping(path = "/{broadcastId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    BroadcastDto publishBroadcast(
            @PathVariable("broadcastId") UUID broadcastId,
            @RequestParam UUID userId,
            @RequestBody BroadcastDto broadcastDto);
}