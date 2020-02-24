package org.solarsystem.web.controller;

import org.solarsystem.web.dao.entity.User;
import org.solarsystem.web.dao.repository.UserRepository;
import org.solarsystem.web.view.AuthorizationView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin1"})
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /*chain.doFilter(req, resp);
        PrintWriter out = resp.getWriter();
        String userLogin = req.getParameter("loginEmail");
        String userPassword = req.getParameter("loginPassword");

        if (userLogin != null && userPassword != null) {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.getUserByEmailByPassword(userLogin, userPassword);
            System.out.println(user);
            if (user != null) {
                out.print("<h3>welcome ADMIN</h3>");
//                chain.doFilter(req, resp);
            } else {
                out.println("<h4>Invalid Login</h4>");
            }
        }*/

        /*if (userLogin.equals("admin@gmail.com") && userPassword.equals("admin")){
            chain.doFilter(req,resp);
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            out.print("username or password error!");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/index.html");
            requestDispatcher.include(req,resp);
            AuthorizationView authorizationView = new AuthorizationView();
            out.println(authorizationView.getAuthorizationPage());
        }*/
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
