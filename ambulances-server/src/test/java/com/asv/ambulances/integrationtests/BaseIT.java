package com.asv.ambulances.integrationtests;

import com.asv.ambulances.domain.model.Ambulance;
import com.asv.ambulances.domain.service.AmbulanceService;
import com.asv.ambulances.domain.service.JourneyService;
import com.asv.ambulances.domain.service.LocateService;
import com.asv.ambulances.domain.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import java.math.BigInteger;
import java.util.Arrays;

@SpringBootTest
public class BaseIT extends AbstractTestNGSpringContextTests {

    @Autowired
    AmbulanceService ambulanceService;

    @Autowired
    JourneyService journeyService;

    @Autowired
    LocateService locateService;

    @Autowired
    TransferService transferService;

    @BeforeMethod
    public void populateData() {
        ambulanceService.addAmbulances(Arrays.asList(
                Ambulance.builder().id("A").seats(BigInteger.valueOf(6)).build(),
                Ambulance.builder().id("B").seats(BigInteger.valueOf(8)).build(),
                Ambulance.builder().id("C").seats(BigInteger.valueOf(2)).build(),
                Ambulance.builder().id("D").seats(BigInteger.valueOf(1)).build(),
                Ambulance.builder().id("E").seats(BigInteger.valueOf(3)).build()
        ));
    }

}

