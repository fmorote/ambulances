package com.asv.ambulances.domain.service;

import com.asv.ambulances.domain.component.Publisher;
import com.asv.ambulances.domain.model.Ambulance;
import com.asv.ambulances.domain.model.Journey;
import com.asv.ambulances.domain.model.Transfer;
import com.asv.ambulances.domain.model.TransferRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

public class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;
    @Mock
    private JourneyService journeyService;
    @Mock
    private LocateService locateService;
    @Mock
    private Publisher publisher;

    private TransferService transferService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transferService = new TransferService(transferRepository, journeyService, locateService, publisher);
    }

    @Test
    public void testAssignTransfers() {
        //Given
        transferService.getWaitingJourneyList()
                .add(Journey.builder().id("1").people(BigInteger.valueOf(2)).build());
        transferService.getWaitingJourneyList()
                .add(Journey.builder().id("2").people(BigInteger.valueOf(3)).build());
        transferService.getWaitingJourneyList()
                .add(Journey.builder().id("3").people(BigInteger.valueOf(5)).build());
        transferService.getWaitingJourneyList()
                .add(Journey.builder().id("4").people(BigInteger.valueOf(1)).build());
        transferService.getWaitingJourneyList()
                .add(Journey.builder().id("5").people(BigInteger.valueOf(3)).build());

        List<Transfer> transfers = new ArrayList<>();
        transfers.add(Transfer.builder().ambulance(Ambulance.builder().id("A").seats(BigInteger.valueOf(6)).build()).build());
        transfers.add(Transfer.builder().ambulance(Ambulance.builder().id("B").seats(BigInteger.valueOf(8)).build()).build());
        transfers.add(Transfer.builder().ambulance(Ambulance.builder().id("C").seats(BigInteger.valueOf(2)).build()).build());
        transfers.add(Transfer.builder().ambulance(Ambulance.builder().id("D").seats(BigInteger.valueOf(1)).build()).build());
        transfers.add(Transfer.builder().ambulance(Ambulance.builder().id("E").seats(BigInteger.valueOf(3)).build()).build());

        when(transferRepository.findAll()).thenReturn(transfers);

        //When
        transferService.assignTransfers();

        //Then
        assertTrue(transferService.getWaitingJourneyList().isEmpty());

    }
}