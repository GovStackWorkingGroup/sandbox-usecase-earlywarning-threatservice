package global.govstack.weather_event_service.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Entity
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

  @OneToMany(mappedBy = "threatEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<CountryThreat> affectedCountries;
  private String range;
  private String notes;
  private LocalDateTime periodStart;
  private LocalDateTime periodEnd;
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    this.threatUUID = UUID.randomUUID();
    this.createdAt = LocalDateTime.now();
  }

}
