package global.govstack.threat_service.controller;

import global.govstack.threat_service.dto.ThreatDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface ThreatControllerInterface {

    @GetMapping()
    Page<ThreatDto> getAllThreats(
        @RequestParam(required = false) String country,
        @RequestParam(required = false, defaultValue = "false") boolean active,
        Pageable pageable
    );

    @GetMapping("/{threatId}")
    ThreatDto getThreat(@PathVariable UUID threatId);

}
