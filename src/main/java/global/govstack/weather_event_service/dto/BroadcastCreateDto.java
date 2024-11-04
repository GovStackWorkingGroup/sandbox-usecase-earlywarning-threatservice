package global.govstack.weather_event_service.dto;


public record BroadcastCreateDto(
        Long threatId,
        String title,
        String notes,
        String englishMsg,
        String swahiliMsg) {
}
