package org.solarsystem.web.dao;

import org.solarsystem.web.entity.Planet;

import java.util.ArrayList;

public interface PlanetDao {
    /*Interface describe methods which should be in the class*/
    public void addPlanet(Planet planet);
    public void updatePlanet(Planet planet);
    public void removePlanet(int id);
    public Planet getPlanetById(int id);
    public ArrayList<String> getPlanetImages(int id);

}
