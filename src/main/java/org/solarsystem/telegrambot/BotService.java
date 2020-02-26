package org.solarsystem.telegrambot;

import java.time.LocalDate;
import java.util.List;

public interface BotService {
    //available body name to calculate distance
    List<String> getAllSpaceBodyNames();

    //available planet name to get description
    List<String> getAllPlanetName();

    double getDistance(String originPlanet, String destinationPlanetUuid, LocalDate date);

    String getInfo(String planetName);

    String getAvailableCommands();



}
