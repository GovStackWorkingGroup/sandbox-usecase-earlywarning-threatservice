package global.govstack.threat_service.controller;

import global.govstack.threat_service.dto.BroadcastCreateDto;
import global.govstack.threat_service.dto.BroadcastDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface BroadcastControllerInterface {

    @GetMapping()
    Page<BroadcastDto> getAllBroadcasts(Pageable pageable);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    BroadcastDto saveBroadcast(@RequestParam UUID userId, @RequestBody BroadcastCreateDto broadcastDto);

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
}