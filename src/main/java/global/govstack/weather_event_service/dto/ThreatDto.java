package global.govstack.weather_event_service.dto;

import java.util.List;


public record ThreatDto(String type, String severity, List<String> affectedCountries, List<String> affectedCounties, String range, String periodStart, String periodEnd) {
}
