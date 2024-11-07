package global.govstack.threat_service.controller;

import global.govstack.threat_service.dto.overview.OverviewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface OverviewControllerInterface {
    @GetMapping()
    OverviewDto getAllThreatsForCountry(@RequestParam String country);
}
