package global.govstack.threat_service.dto.broadcast;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LogInfoDto(String from, String to, String content, LocalDateTime timeStamp, String broadcastId) {
}
