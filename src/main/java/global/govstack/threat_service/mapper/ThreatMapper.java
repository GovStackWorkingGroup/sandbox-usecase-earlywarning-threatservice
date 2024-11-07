package global.govstack.threat_service.mapper;

import global.govstack.threat_service.dto.KafkaThreatDto;
import global.govstack.threat_service.repository.entity.ThreatEvent;
import global.govstack.threat_service.dto.ThreatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThreatMapper {
  @Mapping(source = "id", target = "threatNumber")
  @Mapping(source = "threatUUID", target = "threatId")
  ThreatDto entityToDto(ThreatEvent threatEvent);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "affectedCountries", ignore = true)
  ThreatEvent dtoToEntity(KafkaThreatDto threatDto);
}
