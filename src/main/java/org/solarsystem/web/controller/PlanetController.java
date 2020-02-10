package org.solarsystem.web.controller;

import org.solarsystem.web.dao.PlanetDao;
import org.solarsystem.web.dao.PlanetDaoImp;
import org.solarsystem.web.entity.Planet;

public class PlanetController {
    /*Controller for planet*/
    public static void main(String[] args) {
        /*Temp main method, only testing DB*/
        PlanetDao planetDao = new PlanetDaoImp();
        Planet planet = planetDao.getPlanetById(1);
        System.out.println(planet.toString());
        planetDao.removePlanet(4);

    }
}
