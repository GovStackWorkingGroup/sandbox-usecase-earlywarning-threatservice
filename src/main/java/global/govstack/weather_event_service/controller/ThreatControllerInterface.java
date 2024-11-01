package global.govstack.weather_event_service.controller;

import global.govstack.weather_event_service.dto.ThreatDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ThreatControllerInterface {

    @GetMapping(path = "/getAllThreatsForCountry")
    List<ThreatDto> getAllThreatsForCountry(@RequestParam String country);

}
