package org.solarsystem.web.service;

import java.time.LocalDate;
import java.util.List;

public interface DistanceCalculator {

    double calculateDistance(String originPlanet, String destinationPlanet, LocalDate date);
    List<String> getAvailablePlanet();
}
