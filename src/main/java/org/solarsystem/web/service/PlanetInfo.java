package org.solarsystem.web.service;

import org.solarsystem.web.dao.entity.Planet;

import java.util.List;

public interface PlanetInfo {
    String getShortDescription(String planetName);
    List<String> getAvailablePlanetFromDatabase();
}
