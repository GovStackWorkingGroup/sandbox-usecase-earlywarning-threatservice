package global.govstack.threat_service.pub_sub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.threat_service.controller.exception.InternalServerException;
import global.govstack.threat_service.dto.broadcast.KafkaBroadcastDto;
import global.govstack.threat_service.dto.broadcast.LogInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class IMPublisher {
    private static final String BROADCAST_TOPIC = "broadcast-topic";
    private static final String LOGGING_TOPIC = "log-topic";
    private static final Integer PARTITION = 0;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public void publishBroadcast(KafkaBroadcastDto broadcastDto) {
        log.info("Sending broadcast to IM");
        try {
            this.kafkaTemplate.send(
                    BROADCAST_TOPIC,
                    PARTITION,
                    "some key",
                    mapper.writeValueAsString(broadcastDto));
            this.publishServiceLogging(this.mapper.writeValueAsString(LogInfoDto.builder()
                    .from("Threat Service")
                    .to("Information Mediator BB")
                    .content("Broadcast sent to Information Mediator")
                    .timeStamp(LocalDateTime.now())
                    .build()));
        } catch (JsonProcessingException e) {
            throw new InternalServerException("Something went wrong with publishing message: " + e);
        }
    }

    public void publishServiceLogging(String message) {
        log.info("Sending service logging to IM");
        try {
            this.kafkaTemplate.send(
                    LOGGING_TOPIC,
                    PARTITION,
                    "some key",
                    message);
        } catch (Exception e) {
            throw new InternalServerException("Something went wrong with publishing message: " + e);
        }
    }
}
