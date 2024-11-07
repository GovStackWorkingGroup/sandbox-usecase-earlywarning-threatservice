package global.govstack.threat_service.pub_sub;

import global.govstack.threat_service.service.ThreatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IMListener {
    private static final String THREAT_TOPIC = "threats-topic";

  private final ThreatService threatService;

//    @KafkaListener(groupId = "threat-service-listener", topics = THREAT_TOPIC)
//    public void handleIncomingThreatFromIM(String threatMessage){
//        log.info("Incoming message from threats-topic");
//        this.threatService.handleIncomingThreatFromIM(threatMessage);
//    }
}
