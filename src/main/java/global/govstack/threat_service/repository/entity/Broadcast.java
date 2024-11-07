package global.govstack.threat_service.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Entity
@Data
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
  private BroadcastStatus status;
  private String notes;
  @Column(columnDefinition = "TEXT")
  private String primaryLangMessage;
  @Column(columnDefinition = "TEXT")
  private String secondaryLangMessage;
  private LocalDateTime createdAt;
  private LocalDateTime initiated;

  @PrePersist
  protected void onCreate() {
    broadcastUUID = UUID.randomUUID();
  }
}
