package global.govstack.weather_event_service.controller;

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
  void saveBroadcast(
      @RequestParam UUID userUUID,
      @RequestBody BroadcastDto broadcastDto);
}
