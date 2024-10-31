package global.govstack.weather_event_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.weather_event_service.configuration.UserServiceProperties;

import java.util.List;
import java.util.UUID;

import global.govstack.weather_event_service.dto.ThreatDto;
import global.govstack.weather_event_service.service.location.CountryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@Transactional
public class UserService {

    private final UserServiceProperties userServiceProperties;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;


    public UserService(UserServiceProperties userServiceProperties, ObjectMapper mapper) {
        this.userServiceProperties = userServiceProperties;
        this.mapper = mapper;
        this.restTemplate = new RestTemplate();
    }

    public boolean canBroadcast(UUID userUUID) {
        log.info("check if the administrator has broadcasting rights: {}", userServiceProperties.url());
        return (boolean) restRequest("/api/v1/user/canBroadcast?" + userUUID.toString(), boolean.class);
    }

    public List<CountryDto> getAllCountries() {
        log.info("get all countries: {}", userServiceProperties.url());
        var countries = (String) restRequest("/api/v1/utility/getAllCountries", String.class);
        return mapIncomingMessage(countries);
    }

    public Object restRequest(String endpoint, Class<?> responseType) {
        try {
            return restTemplate
                    .exchange(
                            userServiceProperties.url()
                                    + endpoint,
                            HttpMethod.GET,
                            new HttpEntity<>(null),
                            responseType)
                    .getBody();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }

    private List<CountryDto> mapIncomingMessage(String countries) {
        log.info("Mapping incoming country objects");
        try {
            return this.mapper.readValue(countries, mapper.getTypeFactory().constructCollectionType(List.class, CountryDto.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
