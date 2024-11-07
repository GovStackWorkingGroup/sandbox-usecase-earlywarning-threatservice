package global.govstack.threat_service.dto.overview;

import lombok.Builder;

@Builder
public record OverviewDto(OverviewThreatDto threats,
                          OverviewBroadcastDto broadcasts,
                          OverviewFeedbackDto feedbacks) {
}
