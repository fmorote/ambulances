package com.asv.ambulances.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
public class Transfer implements Comparable {

    @Getter
    private final String id = UUID.randomUUID().toString();
    @NonNull
    private final Ambulance ambulance;

    @Getter
    @Builder.Default
    private List<Journey> journeys = new ArrayList<>();

    public Journey getJourney(String journeyId) {
        return journeys.stream().filter(journey -> journey.getId().equals(journeyId)).findFirst().orElse(null);
    }

    public void removeJourney(String journeyId) {
        Journey journey = getJourney(journeyId);
        if (journey != null) {
            journeys.remove(journey);
        }
    }

    public BigInteger getFreePlaces() {
        return ambulance.getSeats().subtract(BigInteger.valueOf(journeys.stream().mapToInt(journey -> journey.getPeople().intValue()).sum()));
    }

    @Override
    public int compareTo(Object o) {
        return this.getFreePlaces().compareTo(((Transfer) o).getFreePlaces());
    }
}
