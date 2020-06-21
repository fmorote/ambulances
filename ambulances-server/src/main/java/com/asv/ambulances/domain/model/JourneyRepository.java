package com.asv.ambulances.domain.model;

import java.util.Collection;
import java.util.List;

/**
 * Journey Repository
 */
public interface JourneyRepository {
    /**
     * Finds a journey using given id.
     *
     * @param id Id
     * @return Journey if found, else {@code null}
     */
    Journey find(String id);

    /**
     * Finds all journey.
     *
     * @return All journey.
     */
    List<Journey> findAll();

    /**
     * Saves given journey.
     *
     * @param journey journey to save
     */
    void store(Journey journey);

    /**
     * Saves given journeys collection.
     *
     * @param journeys journeys collection to save
     */
    void storeAll(Collection<Journey> journeys);

    /**
     * Removes given journey.
     *
     * @param journey journey to remove
     */
    void remove(Journey journey);

    /**
     * Removes all journeys.
     */
    void clear();

}
