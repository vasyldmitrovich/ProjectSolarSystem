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
        //Add planet to DB
        Planet planetTemp = new Planet(5,"TempPlanet",33.8,10000.1,777.3,true,"Work work )))","Full work description","ua");
        planetDao.addPlanet(planetTemp);
        planetDao.addImageByIdPlanet("/bla/bla/bla.jpg",2);

    }
}
