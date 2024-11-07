package global.govstack.threat_service.controller.impl;

import global.govstack.threat_service.controller.ThreatControllerInterface;
import global.govstack.threat_service.dto.ThreatDto;
import global.govstack.threat_service.service.ThreatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/threats")
public class ThreatControllerImpl implements ThreatControllerInterface {

    private final ThreatService threatService;

    @Override
    public Page<ThreatDto> getAllThreats(String country, boolean active, Pageable pageable) {
        return threatService.getAllThreats(country, active, pageable);
    }
}
