package global.govstack.threat_service.dto.threat;

import global.govstack.threat_service.service.location.MinimalCountryDto;

import java.util.List;
import java.util.UUID;

public record ThreatDto(
    UUID threatId,
    long threatNumber,
    String type,
    String severity,
    List<MinimalCountryDto> affectedCountries,
    String notes,
    String range,
    String periodStart,
    String periodEnd,
    boolean active,
    String createdAt
) {
}
