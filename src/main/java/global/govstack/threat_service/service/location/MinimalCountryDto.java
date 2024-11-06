package global.govstack.threat_service.service.location;

import java.util.List;

public record MinimalCountryDto(Long countryId, String countryName, List<CountyDto> affectedCounties) {
}