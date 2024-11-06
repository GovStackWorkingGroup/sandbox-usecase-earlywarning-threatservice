package global.govstack.threat_service.service.location;

import java.util.List;

public record CountryDto(Long id, String name, List<CountryLanguageDto> countryLanguages, List<CountyDto> counties) {
}