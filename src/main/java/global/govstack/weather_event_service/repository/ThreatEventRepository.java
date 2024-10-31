package global.govstack.weather_event_service.repository;

import global.govstack.weather_event_service.repository.entity.ThreatEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ThreatEventRepository extends JpaRepository<ThreatEvent, Long> {

//    public List<ThreatEvent> getAllThreatsByCountry(String country);
}
