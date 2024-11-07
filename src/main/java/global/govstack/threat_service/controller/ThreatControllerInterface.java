package global.govstack.threat_service.controller;

import global.govstack.threat_service.dto.ThreatDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface ThreatControllerInterface {

    @GetMapping(path = "/getAllThreatsForCountry")
    Page<ThreatDto> getAllThreatsForCountry(@RequestParam String country, Pageable pageable);

}