package com.asv.ambulances.domain.event;

import com.asv.ambulances.domain.model.Journey;
import lombok.Data;

@Data
public class JourneyRequestedEvent {
    private final Journey journey;
}
