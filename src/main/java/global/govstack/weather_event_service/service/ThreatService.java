package global.govstack.weather_event_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.weather_event_service.dto.BroadcastDto;
import global.govstack.weather_event_service.dto.ThreatDto;
import global.govstack.weather_event_service.mapper.ThreatMapper;
import global.govstack.weather_event_service.pub_sub.IMPublisher;
import global.govstack.weather_event_service.repository.ThreatEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ThreatService {

    private final IMPublisher publisher;
    private final ThreatEventRepository threatEventRepository;
    private final ObjectMapper mapper;
    private final ThreatMapper threatMapper;

    public ThreatService(IMPublisher publisher, ObjectMapper mapper, ThreatEventRepository threatEventRepository, ThreatMapper threatMapper) {
        this.publisher = publisher;
        this.mapper = mapper;
        this.threatEventRepository = threatEventRepository;
        this.threatMapper = threatMapper;
    }

    public void handleIncomingThreatFromIM(String threatMessage) {
        final ThreatDto threatDto = this.mapIncomingMessage(threatMessage);
        this.threatEventRepository.save(this.threatMapper.dtoToEntity(threatDto));
    }

    public void publishBroadcast(BroadcastDto broadcastDto) {
        this.publisher.publishBroadcast(broadcastDto);
    }

    public List<ThreatDto> getAllThreats(String country) {
        return this.threatEventRepository.getAllThreatsByCountry(country).stream().map(this.threatMapper::entityToDto).collect(Collectors.toList());
    }

    private ThreatDto mapIncomingMessage(String threatMessage) {
        log.info("Mapping incoming threat object");
        try {
            return this.mapper.readValue(threatMessage, ThreatDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
