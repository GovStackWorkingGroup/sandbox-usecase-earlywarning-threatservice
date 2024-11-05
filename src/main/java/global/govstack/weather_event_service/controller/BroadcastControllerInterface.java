package global.govstack.weather_event_service.controller;

import global.govstack.weather_event_service.dto.BroadcastCreateDto;
import global.govstack.weather_event_service.dto.BroadcastDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface BroadcastControllerInterface {

    @GetMapping(path = "/getAll")
    List<BroadcastDto> getAllBroadcasts();

    @GetMapping(path = "/getBroadcastDetails")
    BroadcastDto getBroadcastDetails(@RequestParam UUID broadcastUUID);

    @PostMapping(path = "/saveBroadcast")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    BroadcastDto saveBroadcast(
            @RequestParam UUID userUUID,
            @RequestBody BroadcastCreateDto broadcastDto);

    @PutMapping(path = "/updateBroadcast")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    BroadcastDto updateBroadcast(
            @RequestParam long id,
            @RequestParam UUID userUUID,
            @RequestBody BroadcastDto broadcastDto);
}