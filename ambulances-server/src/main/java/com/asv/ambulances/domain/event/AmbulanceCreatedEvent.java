package com.asv.ambulances.domain.event;

import com.asv.ambulances.domain.model.Ambulance;
import lombok.Data;

@Data
public class AmbulanceCreatedEvent {
    private final Ambulance ambulance;

}
