package global.govstack.threat_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.threat_service.api.APIUtil;
import global.govstack.threat_service.service.location.CountryDto;
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
public class UserService {

    @Value("${user-service.url}")
    private String USER_SERVICE_URL;
    private final APIUtil apiUtil;
    private final ObjectMapper mapper;
    private final HttpHeaders httpHeaders;


    public UserService(APIUtil apiUtil) {
        this.apiUtil = apiUtil;
        mapper = new ObjectMapper();
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
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
            throw new RuntimeException("Mapping incoming message failed: " + e);
        }
    }
}
