package org.solarsystem.web.controller;

import org.apache.log4j.Logger;
import org.solarsystem.web.dao.repository.PlanetRepository;
import org.solarsystem.web.view.UploadImageView;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "uploadImageServlet", urlPatterns = {"/uploadImageServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class uploadImageServlet extends HttpServlet {

    public static final Logger log = Logger.getLogger(uploadImageServlet.class);
    private static final String SAVE_DIR = "images/resources/uploadFiles";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String userLogin = request.getParameter("loginEmail");
        String userPassword = request.getParameter("loginPassword");
        String sid = request.getParameter("id");
        String pathToFileImage = SAVE_DIR+"/";
        PrintWriter out = response.getWriter();
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        if (sid != null) {
            try {
                int id = Integer.parseInt(sid);
                for (Part part : request.getParts()) {
                    String fileName = extractFileName(part);
                    pathToFileImage = pathToFileImage+fileName;
                    System.out.println(pathToFileImage);
                    // refines the fileName in case it is an absolute path
                    fileName = new File(fileName).getName();

                    part.write(savePath + File.separator + fileName);
                    PlanetRepository planetRepository = new PlanetRepository();
                    planetRepository.addImageByIdPlanet(pathToFileImage,id);
                }
                response.sendRedirect("/admin?loginEmail=" + userLogin + "&loginPassword=" + userPassword);
            } catch (NumberFormatException e) {
                log.info("Not correct id planet" + e);
            }
        } else {
            response.sendRedirect("/admin?loginEmail=" + userLogin + "&loginPassword=" + userPassword);
        }
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userLogin = request.getParameter("loginEmail");
        String userPassword = request.getParameter("loginPassword");
        String sid = request.getParameter("id");
        PrintWriter out = response.getWriter();
        if (sid != null) {
            try {
                int id = Integer.parseInt(sid);
                UploadImageView uploadImageView = new UploadImageView();
                String string = uploadImageView.getPageUploadImage(id, userLogin, userPassword);
                out.println(string);
            } catch (NumberFormatException e) {
                log.info("Not correct id planet" + e);
            }
        } else {
            response.sendRedirect("/admin?loginEmail=" + userLogin + "&loginPassword=" + userPassword);
        }
    }
}
