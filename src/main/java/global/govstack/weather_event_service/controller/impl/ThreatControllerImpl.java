package global.govstack.weather_event_service.controller.impl;

import global.govstack.weather_event_service.controller.ThreatControllerInterface;
import global.govstack.weather_event_service.dto.ThreatDto;
import global.govstack.weather_event_service.service.ThreatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/threat/")
public class ThreatControllerImpl implements ThreatControllerInterface {

    private final ThreatService threatService;


    public ThreatControllerImpl(ThreatService threatService) {
        this.threatService = threatService;
    }

    @Override
    public List<ThreatDto> getAllThreatsForCountry(String country) {
        return this.threatService.getAllThreats(country);
    }

}
