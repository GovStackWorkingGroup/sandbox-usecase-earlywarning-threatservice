package global.govstack.threat_service.dto;

import global.govstack.threat_service.service.location.MinimalCountryDto;

import java.util.List;
import java.util.UUID;

public record ThreatDto(
    UUID threatId,
    long threatNumber,
    String type,
    String severity,
    List<MinimalCountryDto> affectedCountries,
    String range,
    String periodStart,
    String periodEnd,
    boolean active
) {
}
