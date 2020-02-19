package org.solarsystem.web.controller;

import org.solarsystem.web.dao.PlanetDao;
import org.solarsystem.web.dao.repository.PlanetDaoImp;
import org.solarsystem.web.dao.entity.Planet;
import org.solarsystem.web.view.InformationPages;

import java.util.List;

public class PlanetController {
    /*Controller for planet*/
    public static void main(String[] args) {
        PlanetDao planetDao = new PlanetDaoImp();

        Planet planet = planetDao.getPlanetById(3);
        System.out.println(planet);

    }

    public String navBar(){
        PlanetDao planetDao = new PlanetDaoImp();
        List<Planet> planets = planetDao.getAllPlanets();
        InformationPages navTabs = new InformationPages();
        String string = navTabs.navigationTabs(planets);
        return string;
    }

    public String tabContext(){
        PlanetDao planetDao = new PlanetDaoImp();
        List<Planet> planets = planetDao.getAllPlanets();
        InformationPages tabCont = new InformationPages();
        String string = tabCont.tabContent(planets);
        return string;
    }
}
