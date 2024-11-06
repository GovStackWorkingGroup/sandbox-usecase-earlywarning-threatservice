package global.govstack.threat_service.mapper;

import global.govstack.threat_service.dto.BroadcastCreateDto;
import global.govstack.threat_service.dto.BroadcastDto;
import global.govstack.threat_service.repository.entity.Broadcast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BroadcastMapper {
  BroadcastDto entityToDto(Broadcast broadcast);

  Broadcast dtoToEntity(BroadcastDto broadcastDto);

  @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
  Broadcast createDtoToEntity(BroadcastCreateDto broadcastCreateDto);
}
