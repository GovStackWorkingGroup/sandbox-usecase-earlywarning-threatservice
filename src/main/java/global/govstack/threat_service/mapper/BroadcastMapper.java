package global.govstack.threat_service.mapper;

import global.govstack.threat_service.dto.broadcast.BroadcastCreateDto;
import global.govstack.threat_service.dto.broadcast.BroadcastDto;
import global.govstack.threat_service.dto.broadcast.CreateBroadcastCountyDto;
import global.govstack.threat_service.repository.entity.Broadcast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BroadcastMapper {
    @Mapping(source = "id", target = "broadcastNumber")
    @Mapping(source = "broadcastUUID", target = "broadcastId")
    @Mapping(source = "broadcast.threatEvent.threatUUID", target = "threatId")
    @Mapping(source = "broadcast", target = "affectedCounties", qualifiedByName = "assembleAffectedCounties")
    BroadcastDto entityToDto(Broadcast broadcast);

    @Mapping(source = "broadcastId", target = "broadcastUUID")
    Broadcast dtoToEntity(BroadcastDto broadcastDto);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Broadcast createDtoToEntity(BroadcastCreateDto broadcastCreateDto);

    @Named("assembleAffectedCounties")
    default List<CreateBroadcastCountyDto> assembleAffectedCounties(Broadcast broadcast) {
        return broadcast.getAffectedCounties().stream().map(b ->
                        CreateBroadcastCountyDto
                                .builder()
                                .countyId(b.getCountyId())
                                .countyName(b.getCountyName())
                                .build())
                .collect(Collectors.toList());
    }
}
