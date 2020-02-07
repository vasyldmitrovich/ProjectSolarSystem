package org.solarsystem.web.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class SpeedCaclculator3DTest {

    @Test
    void testTimeDestinationFromEarthToMars(){
        SpeedCalculator calc = new SpeedCalculator3D();
        double result = calc.calculateSpeed(UUID.randomUUID().toString(), UUID.randomUUID().toString(), LocalDate.now(), 202.1);
        Assertions.assertEquals(12, result);
    }
}
