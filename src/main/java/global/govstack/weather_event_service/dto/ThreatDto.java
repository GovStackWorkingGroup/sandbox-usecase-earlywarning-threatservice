package global.govstack.weather_event_service.dto;

import global.govstack.weather_event_service.service.location.MinimalCountryDto;

import java.util.List;


public record ThreatDto(String type, String severity, List<MinimalCountryDto> affectedCountries, String range, String periodStart, String periodEnd) {
}
