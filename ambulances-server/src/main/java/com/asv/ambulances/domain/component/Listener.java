package com.asv.ambulances.domain.component;

import com.asv.ambulances.domain.event.AmbulanceCreatedEvent;
import com.asv.ambulances.domain.event.JourneyRequestedEvent;
import com.asv.ambulances.domain.event.TransfersReviewEvent;
import com.asv.ambulances.domain.model.Transfer;
import com.asv.ambulances.domain.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Listener {

    public static final String HANDLE_EVENT = "Handle event ";
    private final TransferService transferService;

    @EventListener
    void handleAmbulanceCreatedEvent(AmbulanceCreatedEvent event) {
        log.info(HANDLE_EVENT + event);
        Transfer transfer = Transfer.builder().ambulance(event.getAmbulance()).build();
        transferService.addTransfer(transfer);
        log.info("Transfer created " + transfer);
    }


    @EventListener
    void handleJourneyRequestedEvent(JourneyRequestedEvent event) {
        log.info(HANDLE_EVENT + event);
        transferService.addJourney(event.getJourney());
        log.info("Journey handled " + event.getJourney());
    }

    @EventListener
    void handleTransfersReviewEvent(TransfersReviewEvent event) {
        log.info(HANDLE_EVENT + event);
        transferService.assignTransfers();
        log.info("Transfers reviewed");
    }
}
