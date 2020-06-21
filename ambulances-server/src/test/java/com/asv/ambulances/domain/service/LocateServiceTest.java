package com.asv.ambulances.domain.service;

import com.asv.ambulances.domain.exception.JourneyException;
import com.asv.ambulances.domain.model.Ambulance;
import com.asv.ambulances.domain.model.Journey;
import com.asv.ambulances.domain.model.Transfer;
import com.asv.ambulances.domain.model.TransferRepository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class LocateServiceTest {


    public static final String JOURNEY_ID = "aJourneyId";
    public static final String AMBULANCE_ID = "anAmbulanceID";
    public static final String OTHER_JOURNEY_ID = "otherJourneyId";
    public static final String OTHER_AMBULANCE_ID = "otherAmbulanceID";

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private JourneyService journeyService;

    private LocateService locateService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        locateService = new LocateService(transferRepository, journeyService);
    }

    @Test
    public void testLocate() throws JourneyException {
        //Given
        Journey journey = Journey.builder().id(JOURNEY_ID).people(BigInteger.ONE).build();
        Journey journey2 = Journey.builder().id(OTHER_JOURNEY_ID).people(BigInteger.ONE).build();
        when(journeyService.searchByJourneyId(JOURNEY_ID)).thenReturn(journey);
        when(journeyService.searchByJourneyId(OTHER_JOURNEY_ID)).thenReturn(journey2);
        List<Transfer> transfers = new ArrayList<>();

        Transfer transfer = Transfer.builder().journeys(Arrays.asList(journey)).ambulance(Ambulance.builder().id(AMBULANCE_ID).seats(BigInteger.ONE).build()).build();
        transfers.add(transfer);
        Transfer otherTransfer = Transfer.builder().journeys(Arrays.asList(journey2)).ambulance(Ambulance.builder().id(OTHER_AMBULANCE_ID).seats(BigInteger.ONE).build()).build();
        transfers.add(otherTransfer);
        when(transferRepository.findAll()).thenReturn(transfers);

        //When
        Transfer actual = locateService.locate(JOURNEY_ID);

        assertEquals(actual, transfer);
    }

    @Test
    public void testLocateWaiting() throws JourneyException {
        //Given
        Journey journey = Journey.builder().id(JOURNEY_ID).people(BigInteger.ONE).build();
        when(journeyService.searchByJourneyId(JOURNEY_ID)).thenReturn(journey);
        List<Transfer> transfers = new ArrayList<>();
        when(transferRepository.findAll()).thenReturn(transfers);

        //When
        Transfer actual = locateService.locate(JOURNEY_ID);

        assertNull(actual);
    }

    @Test(expectedExceptions = JourneyException.class, expectedExceptionsMessageRegExp = "Journey " + JOURNEY_ID + " Not Found.")
    public void testLocateNotFound() throws JourneyException {
        //Given
        when(journeyService.searchByJourneyId(JOURNEY_ID)).thenReturn(null);

        //When
        locateService.locate(JOURNEY_ID);

        assertTrue(false, "Never reach here!!!!");
    }


}