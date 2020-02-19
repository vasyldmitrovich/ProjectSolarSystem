package org.solarsystem.web.service;

import java.time.LocalDate;

public interface DistanceCalculator {

    double calculateDistance(String originPlanet, String destinationPlanet, LocalDate date);
}
