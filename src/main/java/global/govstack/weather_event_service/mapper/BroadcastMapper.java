package global.govstack.weather_event_service.mapper;

import global.govstack.weather_event_service.dto.BroadcastCreateDto;
import global.govstack.weather_event_service.dto.BroadcastDto;
import global.govstack.weather_event_service.repository.entity.Broadcast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BroadcastMapper {
  BroadcastDto entityToDto(Broadcast broadcast);

  Broadcast dtoToEntity(BroadcastDto broadcastDto);

  @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
  Broadcast createDtoToEntity(BroadcastCreateDto broadcastCreateDto);
}
