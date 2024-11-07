package global.govstack.threat_service.repository;

import global.govstack.threat_service.repository.entity.CountyCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyCountryRepository extends JpaRepository<CountyCountry, Long> {
}
