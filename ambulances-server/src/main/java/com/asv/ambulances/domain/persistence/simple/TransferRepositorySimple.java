package com.asv.ambulances.domain.persistence.simple;

import com.asv.ambulances.domain.model.Transfer;
import com.asv.ambulances.domain.model.TransferRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransferRepositorySimple implements TransferRepository {

    private Map<String, Transfer> map = new HashMap<>();

    @Override
    public Transfer find(String id) {
        return map.get(id);
    }

    @Override
    public Transfer findTransferByAmbulance(String id) {
        return map.values().stream()
                .filter(transfer -> transfer.getAmbulance().getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Transfer> findAll() {
        List<Transfer> transfers = new ArrayList<>();
        transfers.addAll(map.values());
        return transfers;
    }

    @Override
    public void store(Transfer transfer) {
        map.put(transfer.getId(), transfer);
    }

    @Override
    public void remove(Transfer transfer) {
        map.remove(transfer.getId());
    }

    @Override
    public void clear() {
        map.clear();
    }
}
