package global.govstack.weather_event_service.controller;

import global.govstack.weather_event_service.dto.overview.OverviewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface OverviewControllerInterface {
    @GetMapping(path = "/getOverviewCount")
    OverviewDto getAllThreatsForCountry(@RequestParam String country);
}
