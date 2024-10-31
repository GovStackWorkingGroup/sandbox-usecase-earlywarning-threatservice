package global.govstack.weather_event_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.weather_event_service.dto.BroadcastDto;
import global.govstack.weather_event_service.dto.KafkaThreatDto;
import global.govstack.weather_event_service.dto.ThreatDto;
import global.govstack.weather_event_service.mapper.ThreatMapper;
import global.govstack.weather_event_service.pub_sub.IMPublisher;
import global.govstack.weather_event_service.repository.CountryThreatRepository;
import global.govstack.weather_event_service.repository.CountyCountryRepository;
import global.govstack.weather_event_service.repository.ThreatEventRepository;
import global.govstack.weather_event_service.repository.entity.CountryThreat;
import global.govstack.weather_event_service.repository.entity.CountyCountry;
import global.govstack.weather_event_service.repository.entity.ThreatEvent;
import global.govstack.weather_event_service.service.location.CountryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ThreatService {

    private final IMPublisher publisher;
    private final ThreatEventRepository threatEventRepository;
    private final ObjectMapper mapper;
    private final ThreatMapper threatMapper;
    private final CountryThreatRepository countryThreatRepository;
    private final CountyCountryRepository countyCountryRepository;
    private final UserService userService;

    public ThreatService(IMPublisher publisher, ObjectMapper mapper, ThreatEventRepository threatEventRepository, ThreatMapper threatMapper, CountryThreatRepository countryThreatRepository, CountyCountryRepository countyCountryRepository, UserService userService) {
        this.publisher = publisher;
        this.mapper = mapper;
        this.threatEventRepository = threatEventRepository;
        this.threatMapper = threatMapper;
        this.countryThreatRepository = countryThreatRepository;
        this.countyCountryRepository = countyCountryRepository;
        this.userService = userService;
    }

    public void handleIncomingThreatFromIM(String threatMessage) {
        final KafkaThreatDto kafkaThreatDto = this.mapIncomingMessage(threatMessage);
        List<CountryDto> allCountries = userService.getAllCountries();
        ThreatEvent threatEvent = this.threatMapper.dtoToEntity(kafkaThreatDto);
        ThreatEvent savedThreat = this.threatEventRepository.save(threatEvent);
        kafkaThreatDto.affectedCountries().forEach(country -> {
            CountryDto countryDto = allCountries.stream().filter(c -> c.name().equals(country)).findFirst().orElseThrow();
            CountryThreat countryThreat = new CountryThreat();
            countryThreat.setThreatEvent(savedThreat);
            countryThreat.setCountryId(countryDto.id());
            countryThreat.setCountryName(countryDto.name());
            CountryThreat countryThreatSaved = countryThreatRepository.save(countryThreat);
            countryDto.counties().forEach(county -> {
                CountyCountry countyCountry = new CountyCountry();
                countyCountry.setCountyId(county.countyId());
                countyCountry.setCountyName(county.countyName());
                countyCountry.setCountryThreat(countryThreatSaved);
                countyCountryRepository.save(countyCountry);
            });
        });
    }

    public void publishBroadcast(BroadcastDto broadcastDto) {
        this.publisher.publishBroadcast(broadcastDto);
    }

    public List<ThreatDto> getAllThreats(String country) {
        List<ThreatEvent> allThreatsByCountry = this.threatEventRepository.getAllThreatsByCountry(country);

        return allThreatsByCountry.stream().map(this.threatMapper::entityToDto).collect(Collectors.toList());
    }

    private KafkaThreatDto mapIncomingMessage(String threatMessage) {
        log.info("Mapping incoming threat object");
        try {
            return this.mapper.readValue(threatMessage, KafkaThreatDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
