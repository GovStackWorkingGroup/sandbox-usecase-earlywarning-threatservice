package global.govstack.weather_event_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WeatherEventServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherEventServiceApplication.class, args);
    }
}
