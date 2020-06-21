package com.asv.ambulances.domain.persistence.simple;

import com.asv.ambulances.domain.model.Ambulance;
import com.asv.ambulances.domain.model.AmbulanceRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AmbulanceRepositorySimple implements AmbulanceRepository {

    private Map<String, Ambulance> map = new HashMap<>();

    @Override
    public Ambulance find(String id) {
        return map.get(id);
    }

    @Override
    public List<Ambulance> findAll() {
        List<Ambulance> ambulances = new ArrayList<>();
        ambulances.addAll(map.values());
        return ambulances;
    }

    @Override
    public void store(Ambulance ambulance) {
        map.put(ambulance.getId(), ambulance);
    }

    @Override
    public void storeAll(Collection<Ambulance> ambulances) {
        map.putAll(ambulances.stream().collect(Collectors.toMap(Ambulance::getId, ambulance -> ambulance)));
    }

    @Override
    public void remove(Ambulance ambulance) {
        map.remove(ambulance.getId());
    }

    @Override
    public void clear() {
        map.clear();
    }
}
