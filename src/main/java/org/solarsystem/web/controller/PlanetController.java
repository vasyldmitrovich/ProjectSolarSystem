package org.solarsystem.web.controller;

import org.solarsystem.web.dao.PlanetDao;
import org.solarsystem.web.dao.PlanetDaoImp;

public class PlanetController {
    /*Controller for planet*/
    public static void main(String[] args) {
        /*Temp main method, only testing DB*/
        PlanetDao planetDao = new PlanetDaoImp();
        planetDao.getPlanetById(1);

        System.out.println(planetDao.toString());


    }
}
