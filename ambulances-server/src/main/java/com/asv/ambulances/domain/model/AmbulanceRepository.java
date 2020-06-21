package com.asv.ambulances.domain.model;

import java.util.Collection;
import java.util.List;

/**
 * Ambulance Repository
 */
public interface AmbulanceRepository {
    /**
     * Finds a ambulance using given id.
     *
     * @param id Id
     * @return Ambulance if found, else {@code null}
     */
    Ambulance find(String id);

    /**
     * Finds all ambulance.
     *
     * @return All ambulance.
     */
    List<Ambulance> findAll();

    /**
     * Saves given ambulance.
     *
     * @param ambulance ambulance to save
     */
    void store(Ambulance ambulance);

    /**
     * Saves given ambulances collection.
     *
     * @param ambulances ambulances collection to save
     */
    void storeAll(Collection<Ambulance> ambulances);

    /**
     * Removes given ambulance.
     *
     * @param ambulance ambulance to remove
     */
    void remove(Ambulance ambulance);

    /**
     * Removes all ambulances.
     */
    void clear();

}
