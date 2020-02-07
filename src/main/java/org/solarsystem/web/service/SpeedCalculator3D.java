package org.solarsystem.web.service;

import java.time.LocalDate;

public class SpeedCalculator3D implements SpeedCalculator {

    @Override
    public double calculateSpeed(String originPlanet, String destinationPlanetUuid, LocalDate date, double speed){
        return 12;
    }
}
