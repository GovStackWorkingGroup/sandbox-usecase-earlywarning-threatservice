package global.govstack.threat_service.dto;

public record BroadcastCreateDto(
    Long threatId,
    String title,
    String notes,
    String primaryLangMessage,
    String secondaryLangMessage) {
}
