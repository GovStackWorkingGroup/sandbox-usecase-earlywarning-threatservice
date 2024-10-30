package global.govstack.weather_event_service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "user-service")
public record UserServiceProperties(String url) {}
