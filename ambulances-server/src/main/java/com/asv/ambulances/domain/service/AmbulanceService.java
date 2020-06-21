package com.asv.ambulances.domain.service;

import com.asv.ambulances.domain.component.Publisher;
import com.asv.ambulances.domain.model.Ambulance;
import com.asv.ambulances.domain.model.AmbulanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmbulanceService {

    private final AmbulanceRepository ambulanceRepository;
    private final JourneyService journeyService;
    private final TransferService transferService;
    private final Publisher publisher;

    public void clearAmbulances() {
        ambulanceRepository.clear();
    }

    public void addAmbulances(List<Ambulance> ambulances) {
        clearAmbulances();
        journeyService.clearJourneys();
        transferService.clearTransfers();
        ambulanceRepository.storeAll(ambulances);
        ambulances.forEach(publisher::publishAmbulanceCreatedEvent);
    }

    public List<Ambulance> retrieveAll() {
        return ambulanceRepository.findAll();
    }

}
