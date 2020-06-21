package com.asv.ambulances.integrationtests;

import com.asv.ambulances.domain.model.Ambulance;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class AmbulanceIT extends BaseIT {

    @Test
    public void clearAmbulances() {

        ambulanceService.addAmbulances(new ArrayList<>());
        assertTrue(ambulanceService.retrieveAll().isEmpty());

        ambulanceService.addAmbulances(Arrays.asList(Ambulance.builder().id("1").seats(BigInteger.ONE).build()));
        List<Ambulance> ambulances = ambulanceService.retrieveAll();
        assertFalse(ambulances.isEmpty());
        assertEquals("1", ambulances.get(0).getId());
        assertEquals(BigInteger.ONE, ambulances.get(0).getSeats());

        ambulanceService.addAmbulances(Arrays.asList(
                Ambulance.builder().id("1").seats(BigInteger.ONE).build(),
                Ambulance.builder().id("2").seats(BigInteger.TEN).build()));
        ambulances = ambulanceService.retrieveAll();
        assertFalse(ambulances.isEmpty());
        assertEquals("1", ambulances.get(0).getId());
        assertEquals(BigInteger.ONE, ambulances.get(0).getSeats());
        assertEquals("2", ambulances.get(1).getId());
        assertEquals(BigInteger.TEN, ambulances.get(1).getSeats());
    }
}
