package global.govstack.weather_event_service.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "country_threat")
public class CountryThreat {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_threat_seq")
  @SequenceGenerator(name = "country_threat_seq", allocationSize = 1)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "threat_id", nullable = false)
  private ThreatEvent threatEvent;
  private Long countryId;
  private String countryName;

}
