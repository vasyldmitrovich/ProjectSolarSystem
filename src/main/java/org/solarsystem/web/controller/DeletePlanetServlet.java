package org.solarsystem.web.controller;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userLogin = request.getParameter("loginEmail");
        String userPassword = request.getParameter("loginPassword");
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        PlanetRepository planetRepository = new PlanetRepository();
        planetRepository.removePlanet(id);

        PrintWriter out = response.getWriter();
        response.sendRedirect("/admin?loginEmail="+userLogin+"&loginPassword="+userPassword);
    }
}
