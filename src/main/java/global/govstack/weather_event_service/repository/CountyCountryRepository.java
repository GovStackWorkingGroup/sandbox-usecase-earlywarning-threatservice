package global.govstack.weather_event_service.repository;

import global.govstack.weather_event_service.repository.entity.CountyCountry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountyCountryRepository extends JpaRepository<CountyCountry, Long> {
}
