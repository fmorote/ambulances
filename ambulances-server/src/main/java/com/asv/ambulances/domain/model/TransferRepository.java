package com.asv.ambulances.domain.model;

import java.util.List;

public interface TransferRepository {

    /**
     * Finds a transfer by id.
     *
     * @param id Id
     * @return Transfer if found, else {@code null}
     */
    Transfer find(String id);

    /**
     * Finds a transfer by ambulance id.
     *
     * @param id Id
     * @return Transfer if found, else {@code null}
     */
    Transfer findTransferByAmbulance(String id);

    /**
     * Finds all transfer.
     *
     * @return All transfer.
     */
    List<Transfer> findAll();

    /**
     * Saves given transfer.
     *
     * @param transfer transfer to save
     */
    void store(Transfer transfer);

    /**
     * Removes given transfer.
     *
     * @param transfer transfer to remove
     */
    void remove(Transfer transfer);

    /**
     * Removes all transfers.
     */
    void clear();


}
