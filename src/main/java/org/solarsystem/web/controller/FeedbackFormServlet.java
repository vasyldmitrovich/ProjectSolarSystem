package org.solarsystem.web.controller;

import org.solarsystem.web.dao.repository.FeedbackRepository;
import org.solarsystem.web.view.FeedbackView;
import org.solarsystem.web.view.IndexSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "/FeedbackFormServlet", /*value = "FeedbackFormServlet",*/

        urlPatterns = {"/FeedbackFormServlet"})
public class FeedbackFormServlet extends HttpServlet {
/*    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        FeedbackView feedbackView = new FeedbackView();
        out.println(feedbackView.getFeedbackPage());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //request.setCharacterEncoding("UTF-8");

        String First_Name = request.getParameter("First_Name");
        String Last_Name = request.getParameter("Last_Name");
        String Email = request.getParameter("Email");
        String Subject = request.getParameter("Subject");
        String Comments = request.getParameter("Comments");
        System.out.println(First_Name);
        FeedbackRepository.addFeedback(First_Name, Last_Name, Email, Subject, Comments);
        doGet(request,response);

        /*PrintWriter printWriter;
        try {
            printWriter = response.getWriter();
            printWriter.println("Feedback send");
        } catch (IOException exc) {
            exc.printStackTrace();
        }*/
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("/html/");
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        indexSingleton.setPath(path);
    }
}


