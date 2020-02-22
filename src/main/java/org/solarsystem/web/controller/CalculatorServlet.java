package org.solarsystem.web.controller;

import org.solarsystem.web.service.CalcDistance;
import org.solarsystem.web.service.PlanetNameArray;
import org.solarsystem.web.view.CalculatorView;
import org.solarsystem.web.view.IndexSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/calculator", urlPatterns = {"/calculator"})
public class CalculatorServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("/html/");
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        indexSingleton.setPath(path);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String fromPlanet = request.getParameter("FromPlanet");
        String toPlanet = request.getParameter("ToPlanet");
        String date = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
        CalcDistance calcDistance = new CalcDistance();
        double distance = calcDistance.getDistance(fromPlanet,toPlanet,date);

        PlanetNameArray planetNameArray = new PlanetNameArray();
        String [] planetNameArr = planetNameArray.getPlanetName();

        CalculatorView calculatorView =new CalculatorView();
        PrintWriter out = response.getWriter();
        out.println(calculatorView.getCalcPageForDoPost(planetNameArr,fromPlanet,toPlanet,date,distance));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fromPlanet = null;
        String toPlanet = null;
        String date = null;
        double distance = 0.0;

        PlanetNameArray planetNameArray = new PlanetNameArray();
        String [] planetNameArr = planetNameArray.getPlanetName();

        CalculatorView calculatorView =new CalculatorView();
        out.println(calculatorView.getCalcPageForDoPost(planetNameArr,fromPlanet,toPlanet,date,distance));
    }


}
