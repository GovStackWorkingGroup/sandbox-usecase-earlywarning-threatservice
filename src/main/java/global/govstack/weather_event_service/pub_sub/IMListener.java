package global.govstack.weather_event_service.pub_sub;

import global.govstack.weather_event_service.service.ThreatService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class IMListener {
    private static final String THREAT_TOPIC = "threats-topic";

  private final ThreatService threatService;

  public IMListener(ThreatService threatService) {
    this.threatService = threatService;
  }

    @KafkaListener(groupId = "test", topics = THREAT_TOPIC)
    public void handleIncomingThreatFromIM(String threatMessage){
        //TODO currently works with localhost:9092 kafka, but not with the cross container communication
        this.threatService.handleIncomingThreatFromIM(threatMessage);
    }
}
