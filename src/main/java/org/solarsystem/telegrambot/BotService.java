package org.solarsystem.telegrambot;

import java.time.LocalDate;
import java.util.List;

public interface BotService {
    List<String> getAllSpaceBodyNames();

    double getDistance(String originPlanet, String destinationPlanetUuid, LocalDate date);

    double getTime(String originPlanet, String destinationPlanetUuid, LocalDate date, double speed);

    String getInfo(String planetName);

    String getAvailableCommands();



}
