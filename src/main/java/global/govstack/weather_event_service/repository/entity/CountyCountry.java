package global.govstack.weather_event_service.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "county_country")
public class CountyCountry {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "county_country_seq")
  @SequenceGenerator(name = "county_country_seq", allocationSize = 1)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "country_threat_id", nullable = false)
  private CountryThreat countryThreat;
  private Long countyId;
  private String countyName;

}
