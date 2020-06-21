package com.asv.ambulances.domain.persistence.simple;

import com.asv.ambulances.domain.model.Journey;
import com.asv.ambulances.domain.model.JourneyRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JourneyRepositorySimple implements JourneyRepository {

    private Map<String, Journey> map = new HashMap<>();

    @Override
    public Journey find(String id) {
        return map.get(id);
    }

    @Override
    public List<Journey> findAll() {
        List<Journey> journeys = Collections.emptyList();
        journeys.addAll(map.values());
        return journeys;
    }

    @Override
    public void store(Journey journey) {
        map.put(journey.getId(), journey);
    }

    @Override
    public void storeAll(Collection<Journey> journeys) {
        journeys.stream().collect(Collectors.toMap(Journey::getId, journey -> journey));
    }

    @Override
    public void remove(Journey journey) {
        map.remove(journey.getId());
    }

    @Override
    public void clear() {
        map.clear();
    }
}
