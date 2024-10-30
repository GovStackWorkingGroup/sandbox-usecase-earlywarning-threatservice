package global.govstack.weather_event_service.pub_sub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.weather_event_service.dto.BroadcastDto;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class IMPublisher {
  private static final String BROADCAST_TOPIC = "broadcast-topic";
  private static final Integer PARTITION = 0;

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper mapper;

  public IMPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.mapper = mapper;
  }

  public ResponseEntity<String> publishBroadcast(BroadcastDto broadcastDto) {
    try {
      // TODO Should be set up to work with JSON instead of string
      this.kafkaTemplate.send(
          BROADCAST_TOPIC,
          PARTITION,
          "some key",
          mapper.writeValueAsString(broadcastDto.id()));
      return ResponseEntity.ok("Message processed from ICPAC");
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
