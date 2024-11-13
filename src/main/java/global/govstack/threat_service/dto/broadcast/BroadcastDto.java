package global.govstack.threat_service.dto.broadcast;

import global.govstack.threat_service.repository.entity.BroadcastStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BroadcastDto(
        UUID broadcastId,
        long broadcastNumber,
        UUID threatId,
        String title,
        BroadcastStatus status,
        String notes,
        String channelType,
        String primaryLangMessage,
        String secondaryLangMessage,
        Long countryId,
        String countryName,
        LocalDateTime periodStart,
        LocalDateTime periodEnd,
        LocalDateTime createdAt,
        LocalDateTime initiated,
        UUID createdBy,
        List<CreateBroadcastCountyDto> affectedCounties
) {
}
