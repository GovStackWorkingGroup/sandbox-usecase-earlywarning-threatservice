package global.govstack.threat_service.repository;

import global.govstack.threat_service.repository.entity.CountryThreat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountryThreatRepository extends JpaRepository<CountryThreat, Long> {
}
