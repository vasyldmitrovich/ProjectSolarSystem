package org.solarsystem.web.service;

import java.time.LocalDate;

public interface SpeedCalculator {

    double calculateSpeed(String originPlanet, String destinationPlanetUuid, LocalDate date, double speed);
}
