package global.govstack.weather_event_service.dto.overview;

import lombok.Builder;

@Builder
public record OverviewBroadcastDto(int sentCount, int pendingCount) {
}
