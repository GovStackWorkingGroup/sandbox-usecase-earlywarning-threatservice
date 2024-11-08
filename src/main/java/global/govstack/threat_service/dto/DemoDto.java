package global.govstack.threat_service.dto;

import global.govstack.threat_service.dto.threat.IncomingThreatDto;

public record DemoDto(
    String key,
    IncomingThreatDto threat
) {
}
