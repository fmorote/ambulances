package com.asv.ambulances.application.controller;

import com.asv.ambulances.domain.model.Ambulance;
import com.asv.ambulances.domain.service.AmbulanceService;
import com.asv.ambulances.domain.service.JourneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ambulances")
@RequiredArgsConstructor
public class AmbulanceController {

    private final AmbulanceService ambulanceService;
    private final JourneyService journeyService;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void addAmbulances(@RequestBody List<Ambulance> ambulances) {
        ambulanceService.clearAmbulances();
        journeyService.clearJourneys();
        ambulanceService.addAmbulances(ambulances);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ambulance> getAmbulances() {
        return ambulanceService.retrieveAll();
    }
}
