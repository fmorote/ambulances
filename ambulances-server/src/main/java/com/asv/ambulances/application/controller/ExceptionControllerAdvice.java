package com.asv.ambulances.application.controller;

import com.asv.ambulances.domain.exception.JourneyException;
import com.asv.ambulances.domain.exception.TransferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(JourneyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String journeyNotFoundHandler(JourneyException ex) {
        log.info(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TransferException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void journeyWaitingHandler(TransferException ex) {
        log.info(ex.getMessage());
    }
}
