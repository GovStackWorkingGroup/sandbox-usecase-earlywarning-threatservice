package global.govstack.weather_event_service.dto.overview;

import lombok.Builder;

@Builder
public record OverviewDto(OverviewThreatDto threats,
                          OverviewBroadcastDto broadcasts,
                          OverviewFeedbackDto feedbacks) {
}
