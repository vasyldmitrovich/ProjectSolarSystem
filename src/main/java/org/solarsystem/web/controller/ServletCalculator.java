package org.solarsystem.web.controller;

import org.solarsystem.web.view.CalculatorView;
import org.solarsystem.web.view.IndexSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletCalculator", urlPatterns = {"/calculator"})
public class ServletCalculator extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        CalculatorView calculatorView =new CalculatorView();

        out.println(calculatorView.getCalculatorPage());
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("/html/");
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        indexSingleton.setPath(path);
    }
}
