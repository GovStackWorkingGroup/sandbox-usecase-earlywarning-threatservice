package global.govstack.threat_service.mapper;

import global.govstack.threat_service.dto.threat.KafkaThreatDto;
import global.govstack.threat_service.repository.entity.ThreatEvent;
import global.govstack.threat_service.dto.threat.ThreatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ThreatMapper {
  @Mapping(source = "id", target = "threatNumber")
  @Mapping(source = "threatUUID", target = "threatId")
  @Mapping(source = "periodStart", target = "periodStart", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
  @Mapping(source = "periodEnd", target = "periodEnd", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
  @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
  @Mapping(source = "threatEvent", target = "active", qualifiedByName = "calculateActive")
  ThreatDto entityToDto(ThreatEvent threatEvent);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "affectedCountries", ignore = true)
  ThreatEvent dtoToEntity(KafkaThreatDto threatDto);

  @Named("calculateActive")
  default boolean calculateActive(ThreatEvent threatEvent) {
    LocalDateTime now = LocalDateTime.now();
    return now.isAfter(threatEvent.getPeriodStart()) && now.isBefore(threatEvent.getPeriodEnd());
  }
}
