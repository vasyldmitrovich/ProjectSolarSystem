package org.solarsystem.web.controller;

import org.solarsystem.web.service.NasaJson;
import org.solarsystem.web.view.CalculatorView;
import org.solarsystem.web.view.IndexSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        String dateFromPage = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
        LocalDate date = LocalDate.parse(dateFromPage, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        NasaJson nasaJson = new NasaJson();
        double distance = nasaJson.calculateDistance(fromPlanet,toPlanet,date);
        List<String> PlanetNameList = nasaJson.getAvailablePlanet();

        CalculatorView calculatorView =new CalculatorView();
        PrintWriter out = response.getWriter();
        out.println(calculatorView.getCalcPageForDoPost(PlanetNameList,fromPlanet,toPlanet,date,distance));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fromPlanet = null;
        String toPlanet = null;
        LocalDate date = null;
        double distance = 0.0;

        NasaJson nasaJson = new NasaJson();
        List<String> PlanetNameList = nasaJson.getAvailablePlanet();

        CalculatorView calculatorView =new CalculatorView();
        out.println(calculatorView.getCalcPageForDoPost(PlanetNameList,fromPlanet,toPlanet,date,distance));
    }


}
