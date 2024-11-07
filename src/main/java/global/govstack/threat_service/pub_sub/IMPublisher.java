package global.govstack.threat_service.pub_sub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.threat_service.dto.KafkaBroadcastDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IMPublisher {
  private static final String BROADCAST_TOPIC = "broadcast-topic";
  private static final Integer PARTITION = 0;

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper mapper;

  public IMPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.mapper = mapper;
  }

  public void publishBroadcast(KafkaBroadcastDto broadcastDto) {
    log.info("Sending broadcast to IM");
    try {
      this.kafkaTemplate.send(
          BROADCAST_TOPIC,
          PARTITION,
          "some key",
          mapper.writeValueAsString(broadcastDto));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Something went wrong with publishing message: " + e);
    }
  }
}