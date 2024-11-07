package global.govstack.threat_service.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "broadcast_county")
public class BroadcastCounty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "broadcast_county_seq")
    @SequenceGenerator(name = "broadcast_county_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broadcast_id", nullable = false)
    private Broadcast broadcast;
    private Long countyId;
    private String countyName;
}
