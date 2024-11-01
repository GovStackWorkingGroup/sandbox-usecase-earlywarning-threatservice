package global.govstack.weather_event_service.dto;


import java.util.Set;


public record KafkaThreatDto(String type, String severity, Set<String> affectedCountries, Set<String> affectedCounties, String range, String periodStart, String periodEnd) {
}