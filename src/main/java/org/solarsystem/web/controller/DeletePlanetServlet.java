package org.solarsystem.web.controller;

import org.apache.log4j.Logger;
import org.solarsystem.web.dao.repository.PlanetRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/DeletePlanetServlet", urlPatterns = {"/deletePlanetAdm"})
public class DeletePlanetServlet extends HttpServlet {

    public static final Logger log = Logger.getLogger(UpdatePlanetShortServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userLogin = request.getParameter("loginEmail");
        String userPassword = request.getParameter("loginPassword");
        String sid = request.getParameter("id");
        PrintWriter out = response.getWriter();
        if (sid != null){
            try {
                int id = Integer.parseInt(sid);
                PlanetRepository planetRepository = new PlanetRepository();
                planetRepository.removePlanet(id);
                response.sendRedirect("/admin?loginEmail="+userLogin+"&loginPassword="+userPassword);
            } catch (NumberFormatException e){
                log.info("Not correct id planet" + e);
            }
        } else {
            response.sendRedirect("/admin?loginEmail="+userLogin+"&loginPassword="+userPassword);
        }
    }
}
