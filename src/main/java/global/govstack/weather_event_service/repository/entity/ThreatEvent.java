package global.govstack.weather_event_service.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "threat")
public class ThreatEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "threat_seq")
  @SequenceGenerator(name = "threat_seq", allocationSize = 1)
  private Long id;
  private UUID threatUUID;
  private String type;
  @Enumerated(EnumType.STRING)
  private ThreatSeverity severity;
  private String country;
  private String county;
  private String range;
  private String notes;
  private LocalDateTime periodStart;
  private LocalDateTime periodEnd;
  private LocalDateTime createdAt;
}
