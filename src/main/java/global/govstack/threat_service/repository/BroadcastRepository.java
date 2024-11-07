package global.govstack.threat_service.repository;

import global.govstack.threat_service.repository.entity.Broadcast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface BroadcastRepository extends ListCrudRepository<Broadcast, Long> {
    Optional<Broadcast> findBroadcastByBroadcastUUID(UUID broadcastId);

    Page<Broadcast> findAll(Pageable pageable);
}
