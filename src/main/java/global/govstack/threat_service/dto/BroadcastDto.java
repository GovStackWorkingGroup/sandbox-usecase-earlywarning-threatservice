package global.govstack.threat_service.dto;

import global.govstack.threat_service.repository.entity.BroadcastStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record BroadcastDto(
    UUID broadcastId,
    UUID threatId,
    String title,
    BroadcastStatus status,
    String notes,
    String primaryLangMessage,
    String secondaryLangMessage,
    LocalDateTime createdAt,
    LocalDateTime initiated
) {
}
