package global.govstack.threat_service.dto.broadcast;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record KafkaBroadcastDto(
        UUID broadcastId,
        String broadcastTitle,
        String channelType,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String textPrimaryLang,
        String textSecondaryLang,
        Long countryId,
        List<Long> countyId,
        UUID publisher
) {
}
