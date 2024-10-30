package global.govstack.weather_event_service.repository;

import global.govstack.weather_event_service.repository.entity.Broadcast;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface BroadcastRepository extends ListCrudRepository<Broadcast, Long> {
    Optional<Broadcast> findBroadcastByBroadcastUUID(UUID broadcastUUID);
}
