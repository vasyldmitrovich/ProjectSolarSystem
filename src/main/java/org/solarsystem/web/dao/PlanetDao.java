package org.solarsystem.web.dao;

import org.solarsystem.web.dao.entity.Planet;

import java.util.ArrayList;
import java.util.List;

public interface PlanetDao {
    /*Interface describe methods which should be in the class*/
    void addPlanet(Planet planet);
    void updatePlanet(Planet planet);
    void removePlanet(int id);
    Planet getPlanetById(int id);
    void addImageByIdPlanet(String pathToTheFile, int idPlanet);
    List<Planet> getAllPlanets();

}
