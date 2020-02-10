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
        System.out.println("This is planet with ID-1: "+planet.toString());
        planetDao.removePlanet(4);
        Planet planet1 = new Planet(5,"ТимчасоваПланета",33.8,10000.1,777.3,true,"Корот. опис укр мовою","Це повний опис по українськи","ua");
        planetDao.updatePlanet(planet1);
        Planet planet2 = planetDao.getPlanetById(5);
        System.out.println("Changed planet with ID-5: "+planet2.toString());

    }
}
