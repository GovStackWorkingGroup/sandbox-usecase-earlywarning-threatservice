package global.govstack.threat_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.threat_service.controller.exception.InternalServerException;
import global.govstack.threat_service.dto.KafkaThreatDto;
import global.govstack.threat_service.dto.ThreatDto;
import global.govstack.threat_service.dto.overview.OverviewThreatDto;
import global.govstack.threat_service.mapper.ThreatMapper;
import global.govstack.threat_service.repository.CountryThreatRepository;
import global.govstack.threat_service.repository.CountyCountryRepository;
import global.govstack.threat_service.repository.ThreatEventRepository;
import global.govstack.threat_service.repository.entity.CountryThreat;
import global.govstack.threat_service.repository.entity.CountyCountry;
import global.govstack.threat_service.repository.entity.ThreatEvent;
import global.govstack.threat_service.repository.entity.ThreatSeverity;
import global.govstack.threat_service.service.location.CountryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThreatService {

    private final ThreatEventRepository threatEventRepository;
    private final ObjectMapper mapper;
    private final ThreatMapper threatMapper;
    private final CountryThreatRepository countryThreatRepository;
    private final CountyCountryRepository countyCountryRepository;
    private final UserService userService;

    public void handleIncomingThreatFromIM(String threatMessage) {
        final KafkaThreatDto kafkaThreatDto = mapIncomingMessage(threatMessage);
        final List<CountryDto> allCountries = userService.getAllCountries();
        final ThreatEvent threatEvent = threatMapper.dtoToEntity(kafkaThreatDto);
        final ThreatEvent savedThreat = threatEventRepository.save(threatEvent);
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

    public Page<ThreatDto> getAllThreats(String country, boolean active, Pageable page) {
        final Page<ThreatEvent> allThreatsByCountry = threatEventRepository.getAllThreats(country, active, page);
        return allThreatsByCountry.map(threatMapper::entityToDto);
    }

    private KafkaThreatDto mapIncomingMessage(String threatMessage) {
        log.info("Mapping incoming threat object");
        try {
            return mapper.readValue(threatMessage, KafkaThreatDto.class);
        } catch (JsonProcessingException e) {
            throw new InternalServerException("Mapping incoming message failed" + e);
        }
    }

    public Optional<ThreatEvent> getThreatEntityById(UUID threatId) {
        return threatEventRepository.findByThreatUUID(threatId);
    }

    public Optional<ThreatDto> getThreatById(UUID threatId) {
        return getThreatEntityById(threatId).map(threatMapper::entityToDto);
    }

    public OverviewThreatDto getThreatCount(String country) {
        final List<ThreatEvent> threatEvents = threatEventRepository.countActiveThreats(country);
        return OverviewThreatDto.builder()
                .activeThreatsCount(threatEvents.size()) //active means start that endDate is > NOW()
                .highPriorityCount((int) threatEvents.stream().filter(t -> t.getSeverity().equals(ThreatSeverity.HIGH)).count())
                .build();
    }
}
