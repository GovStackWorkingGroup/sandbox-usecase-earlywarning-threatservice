package global.govstack.weather_event_service.service.location;

import java.util.List;

public record MinimalCountryDto(Long id, String countryName, List<CountyDto> affectedCounties) {
}