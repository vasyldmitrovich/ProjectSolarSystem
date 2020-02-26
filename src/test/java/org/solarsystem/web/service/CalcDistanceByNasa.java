package org.solarsystem.web.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

 class CalcDistanceByNasa {

    @Test
    void testDistanceFromEarthToMars() {
        DistanceCalculator distanceCalculator = new NasaJson();
        LocalDate localDate = LocalDate.parse("2016-09-09", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        double distance = distanceCalculator.calculateDistance("mars", "earth", localDate);
        double accuracy = 100 * Math.abs(distance - 1.4095169270123702E8/149598000) / (1.4095169270123702E8/149598000);
        Assertions.assertTrue(accuracy <= 0.0001);
    }
}
