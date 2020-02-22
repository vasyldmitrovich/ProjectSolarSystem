package org.solarsystem.web.service;

import java.time.LocalDate;
import java.util.List;
public interface IntervalDistanceCalculator {
    double calculateIntervalDistance(String originPlanet, String destinationPlanet, LocalDate dateStart, LocalDate dateFinish);
    List<String> getAvailablePlanet();
}
