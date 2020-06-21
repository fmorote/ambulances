package com.asv.ambulances.domain.service;

import com.asv.ambulances.domain.component.Publisher;
import com.asv.ambulances.domain.model.Journey;
import com.asv.ambulances.domain.model.JourneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JourneyService {

    private final JourneyRepository journeyRepository;
    private final Publisher publisher;

    public void clearJourneys() {
        journeyRepository.clear();
    }

    public void add(Journey journey) {
        journeyRepository.store(journey);
        publisher.publishJourneyResquestedEvent(journey);
    }

    public Journey searchByJourneyId(String journeyId) {
        return journeyRepository.find(journeyId);
    }

    public void remove(Journey journey) {
        journeyRepository.remove(journey);
    }
}
