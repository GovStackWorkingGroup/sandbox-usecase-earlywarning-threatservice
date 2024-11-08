package global.govstack.threat_service.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    private BroadcastStatus status;
    private String notes;
    private Long countryId;
    private String countryName;
    @Column(columnDefinition = "TEXT")
    private String primaryLangMessage;
    @Column(columnDefinition = "TEXT")
    private String secondaryLangMessage;
    private LocalDateTime periodStart;
    private LocalDateTime periodEnd;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private LocalDateTime initiated;
    @OneToMany(mappedBy = "broadcast", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BroadcastCounty> affectedCounties;


    @PrePersist
    protected void onCreate() {
        broadcastUUID = UUID.randomUUID();
    }
}
