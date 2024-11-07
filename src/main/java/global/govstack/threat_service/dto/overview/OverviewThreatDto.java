package global.govstack.threat_service.dto.overview;

import lombok.Builder;

@Builder
public record OverviewThreatDto(int activeThreatsCount, int highPriorityCount) {
}
