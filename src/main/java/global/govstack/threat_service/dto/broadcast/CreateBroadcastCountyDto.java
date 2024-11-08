package global.govstack.threat_service.dto.broadcast;

import lombok.Builder;

@Builder
public record CreateBroadcastCountyDto(
        Long countyId,
        String countyName) {
}
