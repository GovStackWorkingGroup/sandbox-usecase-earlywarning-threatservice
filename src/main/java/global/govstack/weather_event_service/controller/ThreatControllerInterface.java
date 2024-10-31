package global.govstack.weather_event_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import global.govstack.weather_event_service.dto.DemoDto;
import global.govstack.weather_event_service.dto.ThreatDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ThreatControllerInterface {

//    @GetMapping(path = "/getAllThreatsForCountry")
//    List<ThreatDto> getAllThreatsForCountry(@RequestParam String country);

    @PostMapping(path = "/emitMessage")
    void send(@RequestBody DemoDto message) throws JsonProcessingException;
}
