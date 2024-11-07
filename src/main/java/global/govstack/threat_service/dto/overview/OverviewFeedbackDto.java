package global.govstack.threat_service.dto.overview;

import lombok.Builder;

@Builder
public record OverviewFeedbackDto(int receivedCount, int todayCount) {
}
