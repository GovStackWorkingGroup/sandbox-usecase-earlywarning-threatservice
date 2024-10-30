package global.govstack.weather_event_service.service;

import global.govstack.weather_event_service.configuration.UserServiceProperties;
import java.util.UUID;
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

  public UserService(UserServiceProperties userServiceProperties) {
    this.userServiceProperties = userServiceProperties;
    this.restTemplate = new RestTemplate();
  }

  public boolean canBroadcast(UUID userUUID) {
    log.info("check if the administrator has broadcasting rights: {}", userServiceProperties.url());
    try {

      return restTemplate
          .exchange(
              userServiceProperties.url()
                  + "/api/v1/user/canBroadcast?"
                  + "userUuid="
                  + userUUID.toString(),
              HttpMethod.GET,
              new HttpEntity<>(null),
              boolean.class)
          .getBody();

    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }
}
