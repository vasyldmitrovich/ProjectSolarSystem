package org.solarsystem.web.dao;

import org.solarsystem.web.entity.Planet;

import java.util.ArrayList;

public interface PlanetDao {
    /*Interface describe methods which should be in the class*/
    void addPlanet(Planet planet);
    void updatePlanet(Planet planet);
    void removePlanet(int id);
    Planet getPlanetById(int id);
    ArrayList<String> getPlanetImages(int id);
    void addImageByIdPlanet(String pathToTheFile, int idPlanet);
}
