package org.solarsystem.web.controller;

import org.solarsystem.web.dao.entity.User;
import org.solarsystem.web.dao.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/admin", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String userLogin = request.getParameter("loginEmail");
        String userPassword = request.getParameter("loginPassword");

        if (userLogin != null && userPassword != null){
            UserRepository userRepository = new UserRepository();
            User user = userRepository.getUserByEmailByPassword(userLogin,userPassword);
            System.out.println("This user return null: "+user);
            if (user !=null){
                out.print("<h3>welcome ADMIN</h3>");
            } else {
                out.println("<h4>Invalid Login</h4>");
            }
        }
    }
}
