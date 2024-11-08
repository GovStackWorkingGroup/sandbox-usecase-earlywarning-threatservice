package global.govstack.threat_service.repository;

import global.govstack.threat_service.repository.entity.Broadcast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BroadcastRepository extends ListCrudRepository<Broadcast, Long> {
    Optional<Broadcast> findBroadcastByBroadcastUUID(UUID broadcastId);

    @Query("""
            SELECT b FROM Broadcast b
            WHERE
                (:countryName IS NULL OR b.countryName = :countryName)
                AND
                (:active = false OR (b.periodStart <= CURRENT_TIMESTAMP AND b.periodEnd > CURRENT_TIMESTAMP))
                AND
                (:userUUID IS NULL OR b.createdBy = :userUUID)
                    """)
    Page<Broadcast> findAll(@Param("countryName") String countryName, @Param("active") boolean active, @Param("userUUID") UUID userUUID, Pageable pageable);
}