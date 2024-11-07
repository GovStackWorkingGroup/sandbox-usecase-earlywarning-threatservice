package global.govstack.threat_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.threat_service.util.APIUtil;
import global.govstack.threat_service.controller.exception.InternalServerException;
import global.govstack.threat_service.service.location.CountryDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Value("${user-service.url}")
    private static final String USER_SERVICE_URL = "";

    private final APIUtil apiUtil;
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpHeaders httpHeaders = new HttpHeaders();

    @PostConstruct
    private void init() {
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    public boolean canBroadcast(UUID userUUID) {
        log.info("check if the administrator has broadcasting rights");
        return (boolean) this.restRequest("/api/v1/user/canBroadcast?" + userUUID.toString(), HttpMethod.GET, boolean.class);
    }

    public List<CountryDto> getAllCountries() {
        log.info("get all countries");
        var countries = (String) this.restRequest("/api/v1/utility/getAllCountries", HttpMethod.GET, String.class);
        return mapIncomingMessage(countries);
    }

    public Object restRequest(String endpoint, HttpMethod httpMethod, Class<?> responseType) {
        try {
            return this.apiUtil.callAPI(USER_SERVICE_URL + endpoint, httpMethod, httpHeaders, new HttpEntity<>(null), responseType).getBody();
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
            throw new InternalServerException("Mapping incoming message failed: " + e);
        }
    }
}
