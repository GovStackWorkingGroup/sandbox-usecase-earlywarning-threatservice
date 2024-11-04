package global.govstack.weather_event_service.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "broadcast")
public class Broadcast {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "broadcast_id_seq")
  @SequenceGenerator(name = "broadcast_id_seq", allocationSize = 1)
  private Long id;
  private UUID broadcastUUID;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "threat_id", nullable = false)
  private ThreatEvent threatEvent;
  private String title;
  @Enumerated(EnumType.STRING)
  private EventStatus status;
  private String notes;
  @Column(columnDefinition = "TEXT")
  private String englishMsg;
  @Column(columnDefinition = "TEXT")
  private String swahiliMsg;
  private LocalDateTime createdAt;
  private LocalDateTime initiated;

  @PrePersist
  protected void onCreate() {
    this.broadcastUUID = UUID.randomUUID();
    this.createdAt = LocalDateTime.now();
  }
}
