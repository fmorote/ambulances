package com.asv.ambulances.application.controller;

import com.asv.ambulances.domain.exception.JourneyException;
import com.asv.ambulances.domain.exception.TransferException;
import com.asv.ambulances.domain.model.Ambulance;
import com.asv.ambulances.domain.model.Transfer;
import com.asv.ambulances.domain.service.LocateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locate")
@RequiredArgsConstructor
public class LocateController {

    private final LocateService service;

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Ambulance locate(@RequestParam(name = "ID") String id) throws JourneyException, TransferException {
        Transfer transfer = service.locate(id);
        if (transfer == null) {
            throw new TransferException("Journey " + id + " still waiting");
        }
        return transfer.getAmbulance();
    }
}
