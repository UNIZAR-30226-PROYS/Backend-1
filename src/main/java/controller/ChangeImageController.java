package main.java.controller;

import main.java.model.Usuario;
import java.io.File;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;

/*
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
*/

@WebServlet(urlPatterns = "/changeImage", name = "ChangeImageController")
public class ChangeImageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String)session.getAttribute("username");
        String UA = request.getHeader("User-Agent");
        RequestDispatcher rd = null;
        try {








            if (UA.contains("Mobile")){
                response.sendRedirect("/movil/usuario.jsp");
            }else{
                response.sendRedirect("/escritorio/explorar.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("/movil/usuario.jsp");
            request.setAttribute("error", e.getMessage());
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
