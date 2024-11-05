package global.govstack.weather_event_service.dto;

import global.govstack.weather_event_service.repository.entity.EventStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record BroadcastDto(
        Long id,
        UUID broadcastUUID,
        Long threatId,
        String title,
        EventStatus status,
        String notes,
        String englishMsg,
        String swahiliMsg,
        LocalDateTime createdAt,
        LocalDateTime initiated
) {
}
