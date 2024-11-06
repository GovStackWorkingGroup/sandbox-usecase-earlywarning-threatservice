package global.govstack.threat_service.repository;

import global.govstack.threat_service.repository.entity.ThreatEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ThreatEventRepository extends JpaRepository<ThreatEvent, Long> {

    @Query("SELECT t FROM ThreatEvent t WHERE t IN (SELECT ct.threatEvent FROM CountryThreat ct WHERE ct.countryName = :countryName)")
    Page<ThreatEvent> getAllThreatsByCountry(@Param("countryName") String countryName, Pageable pageable);


}
