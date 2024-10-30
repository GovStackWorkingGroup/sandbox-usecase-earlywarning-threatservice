package global.govstack.weather_event_service.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
public record ThreatDto(String type,
                        String severity,
                        LocalDateTime periodStart,
                        LocalDateTime periodEnd,
                        String country,
                        String county,
                        String range) {
}
