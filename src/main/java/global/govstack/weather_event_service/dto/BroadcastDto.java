package global.govstack.weather_event_service.dto;

import global.govstack.weather_event_service.repository.entity.EventStatus;

import java.time.LocalDateTime;

public record BroadcastDto(
        Long id,
        String title,
        EventStatus status,
        String country,
        String county,
        LocalDateTime periodStart,
        LocalDateTime periodEnd,
        String englishMsg,
        String swahiliMsg) {
}
