package com.asv.ambulances.domain.service;

import com.asv.ambulances.domain.exception.JourneyException;
import com.asv.ambulances.domain.model.Journey;
import com.asv.ambulances.domain.model.Transfer;
import com.asv.ambulances.domain.model.TransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocateService {

    private final TransferRepository transferRepository;
    private final JourneyService journeyService;

    public Transfer locate(String journeyId) throws JourneyException {
        Journey journey = journeyService.searchByJourneyId(journeyId);
        if (journey == null) {
            throw new JourneyException("Journey " + journeyId + " Not Found.");
        }
        List<Transfer> transfers = transferRepository.findAll();
        return transfers.stream().filter(transfer -> transfer.getJourney(journeyId) != null).findFirst().orElse(null);
    }

}
