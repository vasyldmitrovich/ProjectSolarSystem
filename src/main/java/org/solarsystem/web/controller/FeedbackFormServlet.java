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

    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("/html/");
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        indexSingleton.setPath(path);
    }

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
        request.setCharacterEncoding("UTF-8");

        String First_Name = new String (request.getParameter("First_Name").getBytes("iso-8859-1"),
                "UTF-8");
        String Last_Name = new String (request.getParameter("Last_Name").getBytes("iso-8859-1"),
                "UTF-8");
        String Email = new String (request.getParameter("Email").getBytes("iso-8859-1"),
                "UTF-8");
        String Subject = new String (request.getParameter("Subject").getBytes("iso-8859-1"),
                "UTF-8");
        String Comments = new String (request.getParameter("Comments").getBytes("iso-8859-1"),
                "UTF-8");

        FeedbackRepository.addFeedback(First_Name, Last_Name, Email, Subject, Comments);
        doGet(request,response);

        /* String memo = new String(request.getParameter("textInputMemo").getBytes("iso-8859-1"),
                        "UTF-8");*/
    }


}


