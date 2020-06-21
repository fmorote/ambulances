package com.asv.ambulances.application.controller;

import com.asv.ambulances.domain.exception.JourneyException;
import com.asv.ambulances.domain.model.Ambulance;
import com.asv.ambulances.domain.model.Transfer;
import com.asv.ambulances.domain.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService service;

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void transfer(@RequestParam(name = "ID") String id) throws JourneyException {
        service.transfer(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> getTransers() {
        return service.retrieveAll();
    }
}
