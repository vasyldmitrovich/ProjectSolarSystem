package org.solarsystem.web.controller;

import org.solarsystem.web.dao.entity.Planet;
import org.solarsystem.web.dao.repository.PlanetDaoImp;
import org.solarsystem.web.view.UpdatePlanetFullView;
import org.solarsystem.web.view.UpdatePlanetShortView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdatePlanetFullServlet", urlPatterns = {"/updatePlanetFullAdm"})
public class UpdatePlanetFullServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userLogin = request.getParameter("loginEmail");
        String userPassword = request.getParameter("loginPassword");
        String fullDesc = request.getParameter("fullDescription");
        String sid = request.getParameter("idPlanet");
        int id = Integer.parseInt(sid);

        PlanetDaoImp planetDaoImp = new PlanetDaoImp();
        Planet planet = planetDaoImp.getPlanetById(id);
        planet.setFullDescription(fullDesc);
        planetDaoImp.updatePlanet(planet);

        PrintWriter out = response.getWriter();
        out.println("Planet added to DB");
        response.sendRedirect("/admin?loginEmail="+userLogin+"&loginPassword="+userPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userLogin = request.getParameter("loginEmail");
        String userPassword = request.getParameter("loginPassword");
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        PlanetDaoImp planetDaoImp = new PlanetDaoImp();
        Planet planet = planetDaoImp.getPlanetById(id);

        PrintWriter out = response.getWriter();
        UpdatePlanetFullView updatePlanetFullView = new UpdatePlanetFullView();
        String string =updatePlanetFullView.getPageUpdatePlanet(planet,userLogin,userPassword);
        out.println(string);
    }
}
