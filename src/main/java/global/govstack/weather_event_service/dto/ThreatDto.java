package global.govstack.weather_event_service.dto;

import global.govstack.weather_event_service.service.location.MinimalCountryDto;

import java.util.List;
import java.util.UUID;


public record ThreatDto(UUID threatUUID, String type, String severity, List<MinimalCountryDto> affectedCountries, String range, String periodStart, String periodEnd) {
}
