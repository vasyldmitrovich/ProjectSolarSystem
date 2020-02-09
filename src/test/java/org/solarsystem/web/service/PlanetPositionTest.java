package org.solarsystem.web.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetPositionTest {

    PlanetPosition planetPosition = new PlanetPosition("Jupiter", "2004-01-01");
    PlanetPosition planetPositionMars = new PlanetPosition("Mars", "2004-04-01");

    @Test
    void numberOfPlanet() {
        int jupiter = planetPosition.numberOfPlanet("Jupiter");
        Assertions.assertEquals(4, jupiter);

    }

    @Test
    void getJulianDateNumber() {
        double result = planetPosition.getJulianDateNumber("1970-01-01");
        Assertions.assertEquals(2440588, result);
    }

    @Test
    void getMeanAnomaly() {
        double meanAnomaly = planetPosition.getMeanAnomaly();

        double accuracy = 100 * Math.abs(meanAnomaly - 141.4078) / 141.4078;

        Assertions.assertTrue(accuracy <= 0.001);
    }

    @Test
    void getEquationOfCenter() {
        double equationOfCenter = planetPositionMars.getEquationOfCenter();
        double accuracy = 100 * Math.abs(equationOfCenter - 9.4092) / 9.4092;

        Assertions.assertTrue(accuracy <= 0.001);
    }

    @Test
    void getTrueAnomaly() {
        double trueAnomaly = planetPositionMars.getTrueAnomaly();
        double accuracy = Math.abs(trueAnomaly - 122.0623) / 122.0623;
        Assertions.assertTrue(accuracy <= 0.001);
    }

    @Test
    void getEclipticalCoordinates() {
        double eclipticalCoordinates = planetPositionMars.getEclipticalCoordinates();
        double accuracy = Math.abs(eclipticalCoordinates - 13.0664) / 13.0664;
        Assertions.assertTrue(accuracy <= 0.001);
    }

    @Test
    void getRightAscension() {
        double rightAscension = planetPositionMars.getRightAscension();
        double accuracy = Math.abs(rightAscension - 11.8605) / 11.8605;
        Assertions.assertTrue(accuracy <= 0.001);
    }

    @Test
    void getDeclination() {
        double declination = planetPositionMars.getDeclination();
        double accuracy = Math.abs(declination - 5.5221) / 5.5221;
        Assertions.assertTrue(accuracy <= 0.001);
    }

    @Test
    void getDistanceFromSun() {
        double distanceFromSun = planetPosition.getDistanceFromSun();
        double accuracy = Math.abs(distanceFromSun - 5.40406) / 5.40406;
        Assertions.assertTrue(accuracy <= 0.001);
    }

    @Test
    void getXhelio() {
        double xhelio = planetPosition.getXhelio();
        double accuracy = Math.abs(xhelio - (-5.04289)) / 5.04289;
        Assertions.assertTrue(accuracy <= 0.001);
    }

    @Test
    void getYhelio() {
        double yhelio = planetPosition.getYhelio();
        double accuracy = Math.abs(yhelio - 1.93965) / 1.93965;
        System.out.println(yhelio);
        Assertions.assertTrue(accuracy <= 0.005);
    }

    @Test
    void getZhelio() {
        double zhelio = planetPosition.getZhelio();
        double accuracy = Math.abs(zhelio - 0.10478) / 0.10478;
        Assertions.assertTrue(accuracy <= 0.001);
    }
}