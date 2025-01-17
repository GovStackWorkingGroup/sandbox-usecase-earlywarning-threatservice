package global.govstack.threat_service.controller.impl;

import global.govstack.threat_service.controller.OverviewControllerInterface;
import global.govstack.threat_service.dto.overview.OverviewBroadcastDto;
import global.govstack.threat_service.dto.overview.OverviewDto;
import global.govstack.threat_service.dto.overview.OverviewFeedbackDto;
import global.govstack.threat_service.dto.overview.OverviewThreatDto;
import global.govstack.threat_service.service.BroadcastService;
import global.govstack.threat_service.service.ThreatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/overview")
public class OverviewControllerImpl implements OverviewControllerInterface {

    private final ThreatService threatService;
    private final BroadcastService broadcastService;

    @Override
    public OverviewDto getAllThreatsForCountry(String country) {
        final OverviewThreatDto threatDto = this.threatService.getThreatCount(country);
        // temp hardcoded values until the test data is created
        final OverviewBroadcastDto broadcastDto = OverviewBroadcastDto.builder().sentCount(123).pendingCount(3).build();
        final OverviewFeedbackDto feedbackDto = OverviewFeedbackDto.builder().receivedCount(20).todayCount(5).build();
        return OverviewDto.builder()
                .threats(threatDto)
                .broadcasts(broadcastDto)
                .feedbacks(feedbackDto)
                .build();
    }
}
