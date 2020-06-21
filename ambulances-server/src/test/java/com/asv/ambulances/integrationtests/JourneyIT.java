package com.asv.ambulances.integrationtests;

import com.asv.ambulances.domain.exception.JourneyException;
import com.asv.ambulances.domain.model.Journey;
import com.asv.ambulances.domain.model.Transfer;
import com.asv.ambulances.domain.service.LocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigInteger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class JourneyIT extends BaseIT {

    @Autowired
    LocateService locateService;

    @Test
    public void addJourney() throws JourneyException {
        journeyService.add(Journey.builder().id("1").people(BigInteger.valueOf(1)).build());
        Transfer transfer = locateService.locate("1");
        assertEquals("D", transfer.getAmbulance().getId());
    }

    @Test
    public void addWaitingJourney() throws JourneyException {
        journeyService.add(Journey.builder().id("1").people(BigInteger.valueOf(10)).build());
        Transfer transfer = locateService.locate("1");
        assertNull(transfer);
    }
}
