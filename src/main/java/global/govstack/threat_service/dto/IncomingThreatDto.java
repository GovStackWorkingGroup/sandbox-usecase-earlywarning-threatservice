package global.govstack.threat_service.dto;

import java.util.List;
import lombok.Data;


@Data
public class IncomingThreatDto {
    private String type;
    private String severity;
    private List<String> affectedCountries;
    private List<String> affectedCounties;
    private String range;
    private String periodStart;
    private String periodEnd;
}
