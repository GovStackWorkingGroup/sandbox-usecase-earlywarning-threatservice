package global.govstack.weather_event_service.dto.overview;

import lombok.Builder;

@Builder
public record OverviewFeedbackDto(int receivedCount, int todayCount) {
}
