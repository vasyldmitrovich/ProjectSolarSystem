package org.solarsystem.web.controller;

import org.apache.log4j.Logger;
import org.solarsystem.web.dao.entity.Planet;
import org.solarsystem.web.dao.repository.PlanetRepository;
import org.solarsystem.web.view.UpdatePlanetFullView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdatePlanetFullServlet", urlPatterns = {"/updatePlanetFullAdm"})
public class UpdatePlanetFullServlet extends HttpServlet {

    public static final Logger log = Logger.getLogger(UpdatePlanetShortServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userLogin = request.getParameter("loginEmail");
        String userPassword = request.getParameter("loginPassword");
        String fullDesc = request.getParameter("fullDescription");
        String sid = request.getParameter("idPlanet");
        PrintWriter out = response.getWriter();
        if (sid != null) {
            try {
                int id = Integer.parseInt(sid);
                PlanetRepository planetRepository = new PlanetRepository();
                Planet planet = planetRepository.getPlanetById(id);
                planet.setFullDescription(fullDesc);
                planetRepository.updatePlanet(planet);
                out.println("Updating full description planet in DB");
                response.sendRedirect("/admin?loginEmail=" + userLogin + "&loginPassword=" + userPassword);
            } catch (NumberFormatException e) {
                log.info("Not correct id planet" + e);
            }
        } else {
            response.sendRedirect("/admin?loginEmail=" + userLogin + "&loginPassword=" + userPassword);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userLogin = request.getParameter("loginEmail");
        String userPassword = request.getParameter("loginPassword");
        String sid = request.getParameter("id");
        PrintWriter out = response.getWriter();
        if (sid != null) {
            try {
                int id = Integer.parseInt(sid);
                PlanetRepository planetRepository = new PlanetRepository();
                Planet planet = planetRepository.getPlanetById(id);
                UpdatePlanetFullView updatePlanetFullView = new UpdatePlanetFullView();
                String string = updatePlanetFullView.getPageUpdatePlanet(planet, userLogin, userPassword);
                out.println(string);
            } catch (NumberFormatException e) {
                log.info("Not correct id planet" + e);
            }
        } else {
            response.sendRedirect("/admin?loginEmail=" + userLogin + "&loginPassword=" + userPassword);
        }

    }
}
