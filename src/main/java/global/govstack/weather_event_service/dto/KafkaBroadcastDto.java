package global.govstack.weather_event_service.dto;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record KafkaBroadcastDto(
        UUID broadcastUUID,
        String broadcastTitle,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String textPrimaryLang,
        String textSecondaryLang,
        List<Long> countryId,
        List<Long> countyId
) {
}
