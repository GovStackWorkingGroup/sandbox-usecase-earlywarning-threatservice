package global.govstack.weather_event_service.repository.entity;

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
  private String title;
  @Enumerated(EnumType.STRING)
  private EventStatus status;
  private String country;
  private String county;
  private LocalDateTime periodStart;
  private LocalDateTime periodEnd;
  @Column(columnDefinition = "TEXT")
  private String englishMsg;
  @Column(columnDefinition = "TEXT")
  private String swahiliMsg;
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }
}
