package global.govstack.threat_service.repository;

import global.govstack.threat_service.repository.entity.ThreatEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThreatEventRepository extends JpaRepository<ThreatEvent, Long> {

    @Query("SELECT t FROM ThreatEvent t WHERE (:countryName IS NULL OR t IN (SELECT ct.threatEvent FROM CountryThreat ct WHERE ct.countryName = :countryName)) " +
        "AND (:active = false OR (t.periodStart <= CURRENT_TIMESTAMP AND t.periodEnd > CURRENT_TIMESTAMP))")
    Page<ThreatEvent> getAllThreats(@Param("countryName") String countryName, @Param("active") boolean active, Pageable pageable);

    @Query("SELECT t FROM ThreatEvent t WHERE t.periodEnd > CURRENT_TIMESTAMP  and t in (SELECT ct.threatEvent FROM CountryThreat ct WHERE ct.countryName = :countryName)")
    List<ThreatEvent> countActiveThreats(@Param("countryName") String countryName);


}
