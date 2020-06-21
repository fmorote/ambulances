package com.asv.ambulances.integrationtests;

import com.asv.ambulances.domain.exception.JourneyException;
import com.asv.ambulances.domain.model.Journey;
import com.asv.ambulances.domain.model.Transfer;
import org.testng.annotations.Test;

import java.math.BigInteger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TransferIT extends BaseIT {

    @Test
    public void transfer() throws JourneyException {

        journeyService.add(Journey.builder().id("1").people(BigInteger.valueOf(6)).build());
        journeyService.add(Journey.builder().id("2").people(BigInteger.valueOf(8)).build());
        journeyService.add(Journey.builder().id("3").people(BigInteger.valueOf(2)).build());
        journeyService.add(Journey.builder().id("4").people(BigInteger.valueOf(1)).build());
        journeyService.add(Journey.builder().id("5").people(BigInteger.valueOf(3)).build());
        Transfer transfer = locateService.locate("1");
        assertEquals("A", transfer.getAmbulance().getId());
        transfer = locateService.locate("2");
        assertEquals("B", transfer.getAmbulance().getId());
        transfer = locateService.locate("3");
        assertEquals("C", transfer.getAmbulance().getId());
        transfer = locateService.locate("4");
        assertEquals("D", transfer.getAmbulance().getId());
        transfer = locateService.locate("5");
        assertEquals("E", transfer.getAmbulance().getId());

        transferService.transfer("1");
        transfer = locateService.locate("1");
        assertNull(transfer);
        assertEquals(transferService.findTransferByAmbulance("A").getFreePlaces(), BigInteger.valueOf(6));

        journeyService.add(Journey.builder().id("6").people(BigInteger.valueOf(3)).build());
        assertEquals(transferService.findTransferByAmbulance("A").getFreePlaces(), BigInteger.valueOf(3));
        transfer = locateService.locate("6");
        assertEquals("A", transfer.getAmbulance().getId());
        journeyService.add(Journey.builder().id("7").people(BigInteger.valueOf(3)).build());
        assertEquals(transferService.findTransferByAmbulance("A").getFreePlaces(), BigInteger.valueOf(0));
        transfer = locateService.locate("7");
        assertEquals("A", transfer.getAmbulance().getId());

        journeyService.add(Journey.builder().id("8").people(BigInteger.valueOf(3)).build());
        transfer = locateService.locate("8");
        assertNull(transfer);
        transferService.transfer("3");
        transfer = locateService.locate("8");
        assertNull(transfer);
        transferService.transfer("2");
        transfer = locateService.locate("8");
        assertEquals("B", transfer.getAmbulance().getId());
        assertEquals(transferService.findTransferByAmbulance("B").getFreePlaces(), BigInteger.valueOf(5));

    }

    @Test
    public void addWaitingJourney() throws JourneyException {
        journeyService.add(Journey.builder().id("1").people(BigInteger.valueOf(10)).build());
        Transfer transfer = locateService.locate("1");
        assertNull(transfer);
    }
}
