package org.solarsystem.web.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcDistanceTest {

    @Test
    void getDistance() {
        double distance = CalcDistance.getDistance("Mars", "Earth", "2020-09-05");
        double accuracy = 100 * Math.abs(distance - 0.4803) / 0.4803;
        Assertions.assertTrue(accuracy <= 0.75);

    }
}