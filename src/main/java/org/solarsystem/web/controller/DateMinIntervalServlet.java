package org.solarsystem.web.controller;

import org.solarsystem.web.service.IntervalNasaJson;
import org.solarsystem.web.service.NasaJson;
import org.solarsystem.web.view.DateMinIntervalView;
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

@WebServlet(name = "/DateMinInterval", urlPatterns = {"/DateMinInterval"})
public class DateMinIntervalServlet extends HttpServlet {

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
        LocalDate dateStart = LocalDate.parse(request.getParameter("dateStart"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateFinish = LocalDate.parse(request.getParameter("dateFinish"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        NasaJson nasaJson = new NasaJson();
        List<String> PlanetNameList = nasaJson.getAvailablePlanet();

        IntervalNasaJson intervalNasaJson = new IntervalNasaJson();
        double distance = intervalNasaJson.calculateIntervalDistance(fromPlanet,toPlanet,dateStart,dateFinish);
        String dist = String.valueOf((distance));
        String dateMinInterval = intervalNasaJson.calculateDateMinInterval(fromPlanet,toPlanet,dateStart,dateFinish);

        DateMinIntervalView dateMinIntervalView = new DateMinIntervalView();
        PrintWriter printWriter = response.getWriter();
        printWriter.println(dateMinIntervalView.getDateMinInterval(PlanetNameList,dateMinInterval,dist));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String date = null;
        String distance = null;

        NasaJson nasaJson = new NasaJson();
        List<String> PlanetNameList = nasaJson.getAvailablePlanet();

        DateMinIntervalView dateMinIntervalView = new DateMinIntervalView();
        PrintWriter out = response.getWriter();
        out.println(dateMinIntervalView.getDateMinInterval(PlanetNameList,date,distance));

    }
}
