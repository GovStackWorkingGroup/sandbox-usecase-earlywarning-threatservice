package global.govstack.weather_event_service.mapper;

import global.govstack.weather_event_service.repository.entity.ThreatEvent;
import global.govstack.weather_event_service.dto.ThreatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThreatMapper {
  ThreatDto entityToDto(ThreatEvent threatEvent);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  ThreatEvent dtoToEntity(ThreatDto threatDto);
}
