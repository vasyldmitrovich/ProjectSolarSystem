package org.solarsystem.web.controller;

import org.checkerframework.checker.units.qual.A;
import org.solarsystem.web.dao.PlanetDao;
import org.solarsystem.web.dao.repository.PlanetDaoImp;
import org.solarsystem.web.dao.entity.Planet;
import org.solarsystem.web.view.AdminView;
import org.solarsystem.web.view.InformationView;

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
        InformationView navTabs = new InformationView();
        String string = navTabs.navigationTabs(planets);
        return string;
    }

    public String tabContext(){
        PlanetDao planetDao = new PlanetDaoImp();
        List<Planet> planets = planetDao.getAllPlanets();
        InformationView tabCont = new InformationView();
        String string = tabCont.tabContent(planets);
        return string;
    }

    public String fullPage(String addToBodyPage){
        InformationView fullPage = new InformationView();
        String fullBody = fullPage.returnFullPage(addToBodyPage);
        return fullBody;
    }

    public String adminView(String loginEmail, String loginPassword){
        PlanetDao planetDao = new PlanetDaoImp();
        List<Planet> planets = planetDao.getAllPlanets();
        AdminView adminView = new AdminView();
        String fullPage = adminView.getFullPage(planets,loginEmail,loginPassword);
        return fullPage;
    }

}
