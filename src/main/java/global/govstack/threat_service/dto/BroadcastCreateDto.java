package global.govstack.threat_service.dto;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BroadcastCreateDto(
        UUID threatId,
        String title,
        String notes,
        String primaryLangMessage,
        String secondaryLangMessage,
        Long countryId,
        String countryName,
        LocalDateTime periodStart,
        LocalDateTime periodEnd,
        List<CreateBroadcastCountyDto>affectedCounties) {
}
