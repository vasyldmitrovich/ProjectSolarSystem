package org.solarsystem.web.controller;

import org.solarsystem.web.view.InfoSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletIndex", urlPatterns = {"/"}, loadOnStartup = 1)
public class IndexServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        String patch = getServletContext().getRealPath("/html/");
        InfoSingleton infoSingleton = InfoSingleton.getInstance();
        infoSingleton.setPatch(patch);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
        PlanetController planetController = new PlanetController();
            String fullBody = planetController.fullPage(planetController.navBar()+planetController.tabContext());
        PrintWriter printWriter = response.getWriter();
        printWriter.println(fullBody);
    }

}
