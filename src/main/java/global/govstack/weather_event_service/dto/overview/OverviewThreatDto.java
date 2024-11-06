package global.govstack.weather_event_service.dto.overview;

import lombok.Builder;

@Builder
public record OverviewThreatDto(int activeThreatsCount, int highPriorityCount) {
}
