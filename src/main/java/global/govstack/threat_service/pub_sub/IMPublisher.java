package global.govstack.threat_service.pub_sub;

import global.govstack.threat_service.controller.exception.InternalServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IMPublisher {
    private static final String BROADCAST_TOPIC = "broadcast-topic";
    private static final String LOGGING_TOPIC = "log-topic";
    private static final Integer PARTITION = 0;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishBroadcast(String message) {
        log.info("Sending broadcast to IM");
        try {
            this.kafkaTemplate.send(
                    BROADCAST_TOPIC,
                    PARTITION,
                    "some key",
                    message);
        } catch (Exception e) {
            throw new InternalServerException("Something went wrong with publishing to broadcast topic: " + e);
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
            throw new InternalServerException("Something went wrong with publishing to service log: " + e);
        }
    }
}
