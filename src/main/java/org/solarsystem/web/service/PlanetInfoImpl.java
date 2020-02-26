package org.solarsystem.web.service;

import org.solarsystem.web.dao.entity.Planet;
import org.solarsystem.web.dao.repository.PlanetRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PlanetInfoImpl implements PlanetInfo {
    @Override
    public String getShortDescription(String planetName) {
        String shortDescription;
        List<Planet> allPlanets = new PlanetRepository().getAllPlanets();
        List<String> collect = allPlanets.stream().map(Planet::getName).map(String::toLowerCase).collect(Collectors.toList());

        if (collect.contains(planetName)){
            shortDescription = allPlanets.stream().filter(e -> e.getName().equalsIgnoreCase(planetName))
                    .collect(Collectors.toList()).get(0).getShortDescription();

        }else{
            shortDescription="not found";
        }
        return shortDescription;
    }

    @Override
    public List<String> getAvailablePlanetFromDatabase() {
        return new PlanetRepository().getAllPlanets().stream().map(Planet::getName).collect(Collectors.toList());
    }
}
