package com.asv.ambulances.domain.component;

import com.asv.ambulances.domain.event.AmbulanceCreatedEvent;
import com.asv.ambulances.domain.event.JourneyRequestedEvent;
import com.asv.ambulances.domain.event.TransfersReviewEvent;
import com.asv.ambulances.domain.model.Ambulance;
import com.asv.ambulances.domain.model.Journey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Publisher {

    public static final String SEND_EVENT = "Send event ";
    private final ApplicationEventPublisher eventPublisher;

    public void publishAmbulanceCreatedEvent(final Ambulance ambulance) {
        AmbulanceCreatedEvent event = new AmbulanceCreatedEvent(ambulance);
        log.info(SEND_EVENT + event);
        eventPublisher.publishEvent(event);
    }

    public void publishJourneyResquestedEvent(final Journey journey) {
        JourneyRequestedEvent event = new JourneyRequestedEvent(journey);
        log.info(SEND_EVENT + event);
        eventPublisher.publishEvent(event);
    }

    public void publishTransfersReviewEvent() {
        TransfersReviewEvent event = new TransfersReviewEvent();
        log.info(SEND_EVENT + event);
        eventPublisher.publishEvent(event);
    }
}
