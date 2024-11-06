package global.govstack.threat_service.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "country_threat")
public class CountryThreat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_threat_seq")
    @SequenceGenerator(name = "country_threat_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "threat_id", nullable = false)
    private ThreatEvent threatEvent;
    private Long countryId;
    private String countryName;
    @OneToMany(mappedBy = "countryThreat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CountyCountry> affectedCounties;


}
