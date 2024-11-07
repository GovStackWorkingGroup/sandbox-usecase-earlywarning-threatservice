package global.govstack.threat_service.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record KafkaBroadcastDto(
    UUID broadcastUUID,
    String broadcastTitle,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String textPrimaryLang,
    String textSecondaryLang,
    Long countryId,
    List<Long> countyId
) {
}
