package org.solarsystem.web.controller;

import org.solarsystem.web.dao.entity.User;
import org.solarsystem.web.dao.repository.UserRepository;
import org.solarsystem.web.view.AuthorizationView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin"})
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");

        String userLogin = req.getParameter("loginEmail");
        String userPassword = req.getParameter("loginPassword");

        if (userLogin != null && userPassword != null) {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.getUserByEmailByPassword(userLogin, userPassword);

            if (user != null) {
                chain.doFilter(req, resp);
            } else {
                out.print("Username or password error!");
                AuthorizationView authorizationView = new AuthorizationView();
                out.println(authorizationView.getAuthorizationPage());
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
