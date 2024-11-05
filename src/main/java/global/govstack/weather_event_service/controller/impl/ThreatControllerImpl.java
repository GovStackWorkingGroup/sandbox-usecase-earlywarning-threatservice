package global.govstack.weather_event_service.controller.impl;

import global.govstack.weather_event_service.controller.ThreatControllerInterface;
import global.govstack.weather_event_service.dto.ThreatDto;
import global.govstack.weather_event_service.service.ThreatService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/threat/")
public class ThreatControllerImpl implements ThreatControllerInterface {

    private final ThreatService threatService;


    public ThreatControllerImpl(ThreatService threatService) {
        this.threatService = threatService;
    }

    @Override
    public Page<ThreatDto> getAllThreatsForCountry(String country, Pageable pageable) {
        return this.threatService.getAllThreats(country, pageable);
    }
}
