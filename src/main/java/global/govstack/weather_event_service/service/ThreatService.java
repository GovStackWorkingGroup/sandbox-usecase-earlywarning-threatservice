package global.govstack.weather_event_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.weather_event_service.dto.KafkaThreatDto;
import global.govstack.weather_event_service.dto.ThreatDto;
import global.govstack.weather_event_service.mapper.ThreatMapper;
import global.govstack.weather_event_service.repository.CountryThreatRepository;
import global.govstack.weather_event_service.repository.CountyCountryRepository;
import global.govstack.weather_event_service.repository.ThreatEventRepository;
import global.govstack.weather_event_service.repository.entity.CountryThreat;
import global.govstack.weather_event_service.repository.entity.CountyCountry;
import global.govstack.weather_event_service.repository.entity.ThreatEvent;
import global.govstack.weather_event_service.service.location.CountryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ThreatService {

    private final ThreatEventRepository threatEventRepository;
    private final ObjectMapper mapper;
    private final ThreatMapper threatMapper;
    private final CountryThreatRepository countryThreatRepository;
    private final CountyCountryRepository countyCountryRepository;
    private final UserService userService;

    public ThreatService(ObjectMapper mapper, ThreatEventRepository threatEventRepository, ThreatMapper threatMapper, CountryThreatRepository countryThreatRepository, CountyCountryRepository countyCountryRepository, UserService userService) {
        this.mapper = mapper;
        this.threatEventRepository = threatEventRepository;
        this.threatMapper = threatMapper;
        this.countryThreatRepository = countryThreatRepository;
        this.countyCountryRepository = countyCountryRepository;
        this.userService = userService;
    }

    public void handleIncomingThreatFromIM(String threatMessage) {
        final KafkaThreatDto kafkaThreatDto = this.mapIncomingMessage(threatMessage);
        final List<CountryDto> allCountries = userService.getAllCountries();
        final ThreatEvent threatEvent = this.threatMapper.dtoToEntity(kafkaThreatDto);
        final ThreatEvent savedThreat = this.threatEventRepository.save(threatEvent);
        kafkaThreatDto.affectedCountries().forEach(country -> {
            CountryDto countryDto = allCountries.stream().filter(c -> c.name().equals(country)).findFirst().orElseThrow();
            CountryThreat countryThreat = new CountryThreat();
            countryThreat.setThreatEvent(savedThreat);
            countryThreat.setCountryId(countryDto.id());
            countryThreat.setCountryName(countryDto.name());
            final CountryThreat countryThreatSaved = countryThreatRepository.save(countryThreat);
            countryDto.counties().forEach(county -> {
                CountyCountry countyCountry = new CountyCountry();
                countyCountry.setCountyId(county.countyId());
                countyCountry.setCountyName(county.countyName());
                countyCountry.setCountryThreat(countryThreatSaved);
                countyCountryRepository.save(countyCountry);
            });
        });
    }


    public Page<ThreatDto> getAllThreats(String country, Pageable page) {
        final Page<ThreatEvent> allThreatsByCountry = this.threatEventRepository.getAllThreatsByCountry(country, page);
        return allThreatsByCountry.map(this.threatMapper::entityToDto);
    }

    private KafkaThreatDto mapIncomingMessage(String threatMessage) {
        log.info("Mapping incoming threat object");
        try {
            return this.mapper.readValue(threatMessage, KafkaThreatDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Mapping incoming message failed" + e);
        }
    }

    public Optional<ThreatEvent> getThreatById(Long threatId) {
        return this.threatEventRepository.findById(threatId);
    }
}
