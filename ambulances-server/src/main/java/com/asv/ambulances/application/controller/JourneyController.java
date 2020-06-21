package com.asv.ambulances.application.controller;

import com.asv.ambulances.domain.model.Journey;
import com.asv.ambulances.domain.service.JourneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journey")
@RequiredArgsConstructor
public class JourneyController {

    private final JourneyService journeyService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void journey(@RequestBody Journey journey) {
        journeyService.add(journey);
    }
}
