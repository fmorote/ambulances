package com.asv.ambulances.domain.service;

import com.asv.ambulances.domain.component.Publisher;
import com.asv.ambulances.domain.exception.JourneyException;
import com.asv.ambulances.domain.model.Journey;
import com.asv.ambulances.domain.model.Transfer;
import com.asv.ambulances.domain.model.TransferRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final JourneyService journeyService;
    private final LocateService locateService;
    private final Publisher publisher;

    @Getter
    private LinkedList<Journey> waitingJourneyList = new LinkedList<>();

    public void clearTransfers() {
        transferRepository.clear();
    }

    public void transfer(String journeyId) throws JourneyException {
        Transfer transfer = locateService.locate(journeyId);
        // on transfer
        if (transfer != null) {
            transfer.removeJourney(journeyId);
            publisher.publishTransfersReviewEvent();
        } else { // waiting
            Journey journey = journeyService.searchByJourneyId(journeyId);
            journeyService.remove(journey);
            waitingJourneyList.remove(journey);
        }
    }

    public void addJourney(Journey journey) {
        waitingJourneyList.add(journey);
        publisher.publishTransfersReviewEvent();
    }

    @Synchronized
    public void assignTransfers() {
        log.info("assignTransfers");
        List<Journey> toRemove = new ArrayList<>();
        for (Journey journey : waitingJourneyList) {
            List<Transfer> transfers = transferRepository.findAll();
            Transfer best = transfers.stream()
                    .filter(transfer -> transfer.getFreePlaces().compareTo(journey.getPeople()) >= 0)
                    .sorted()
                    .findFirst().orElse(null);
            if (best != null) {
                best.getJourneys().add(journey);
                transferRepository.store(best);
                toRemove.add(journey);
            }
        }
        waitingJourneyList.removeAll(toRemove);
    }

    public void addTransfer(Transfer transfer) {
        transferRepository.store(transfer);
    }

    public Transfer findTransferByAmbulance(String id) {
        return transferRepository.findTransferByAmbulance(id);
    }

    public List<Transfer> retrieveAll() {
        return transferRepository.findAll();
    }
}
