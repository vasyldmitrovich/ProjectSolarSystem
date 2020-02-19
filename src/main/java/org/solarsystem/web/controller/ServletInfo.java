package org.solarsystem.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet", urlPatterns = "/Info")
public class ServletInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PlanetController planetController = new PlanetController();

    String string = planetController.navBar();
    String string1 = planetController.tabContext();
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<!DOCTYPE html>\n" +
                "<html lang=\"ua\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "\n" +
                "    <!-- Bootstrap CSS -->\n" +
                "    <link rel=\"stylesheet\" href=\"/css/bootstrap.min.css\">\n" +
                "    <title>Solar System</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +string+string1+
                "\n" +
                "<!--#######-->\n" +
                "<script src=\"/js/jquery-3.4.1.min.js\"></script>\n" +
                "<script src=\"/js/bootstrap.bundle.min.js\"></script>\n" +
                "</body>\n" +
                "</html>");
    }
}
