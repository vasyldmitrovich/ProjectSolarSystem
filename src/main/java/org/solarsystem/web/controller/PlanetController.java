package org.solarsystem.web.controller;

import org.solarsystem.web.dao.PlanetDao;
import org.solarsystem.web.dao.repository.PlanetRepository;
import org.solarsystem.web.dao.entity.Planet;
import org.solarsystem.web.view.AdminView;
import org.solarsystem.web.view.InformationView;

import java.util.List;
/*Controller planets for index and admin pages*/
public class PlanetController {

    public static void main(String[] args) {
    String temp = "1";
    int i = Integer.parseInt(temp);
        System.out.println(i);
    }

    public String navBar(){
        PlanetDao planetDao = new PlanetRepository();
        List<Planet> planets = planetDao.getAllPlanets();
        InformationView navTabs = new InformationView();
        String string = navTabs.navigationTabs(planets);
        return string;
    }

    public String tabContext(){
        PlanetDao planetDao = new PlanetRepository();
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
        PlanetDao planetDao = new PlanetRepository();
        List<Planet> planets = planetDao.getAllPlanets();
        AdminView adminView = new AdminView();
        String fullPage = adminView.getFullPage(planets,loginEmail,loginPassword);
        return fullPage;
    }

}
