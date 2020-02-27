package org.solarsystem.web.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class IntervalNasaJsonTest {

    private static int count;

    @BeforeEach
    void setUp() {
        System.out.println("This executes before any test cases. Count = " + count++);
    }

    @AfterEach
    void tearDown() {
        System.out.println("This executes before any test cases. Count = " + count++);
    }


    @Test
    void calculateIntervalDistance() {

        IntervalDistanceCalculator intervalDistanceCalculator = new IntervalNasaJson();
        LocalDate dateStart = LocalDate.parse("1999-01-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateFinish = LocalDate.parse("2002-12-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        double distance = intervalDistanceCalculator.calculateIntervalDistance("mars", "earth", dateStart, dateFinish);
        Assertions.assertEquals(0.4501663931405156, distance, 0);
    }

    @Test
    void calculateDateMinInterval() {

        IntervalMinDateCalulator intervalMinDateCalulator = new IntervalNasaJson();
        LocalDate dateStart = LocalDate.parse("2034-01-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateFinish = LocalDate.parse("2037-01-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dateMinInterval= intervalMinDateCalulator.calculateDateMinInterval("mars", "pluto", dateStart, dateFinish);
        Assertions.assertEquals("2035-07-17 00:00:00.000000 UTC", dateMinInterval);
    }

    @Test
    void getAvailablePlanet() {
    }

    @Test
    void isPlanetToCalc() {
    }
}