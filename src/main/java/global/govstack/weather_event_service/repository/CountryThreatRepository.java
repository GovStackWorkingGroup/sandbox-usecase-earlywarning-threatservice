package global.govstack.weather_event_service.repository;

import global.govstack.weather_event_service.repository.entity.CountryThreat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountryThreatRepository extends JpaRepository<CountryThreat, Long> {
}
