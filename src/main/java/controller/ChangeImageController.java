package main.java.controller;

import java.util.*;
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


import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;



@WebServlet(urlPatterns = "/changeImage", name = "ChangeImageController")
public class ChangeImageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String)session.getAttribute("username");
        String UA = request.getHeader("User-Agent");
        RequestDispatcher rd = null;
        try {
            String user = (String)session.getAttribute("username");

            File file ;
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
            String filePath = "/contenido/imagenes/usuarios/";

            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(maxMemSize);
                factory.setRepository(new File("/contenido"));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax( maxFileSize );
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while ( i.hasNext () )
                {
                    FileItem fi = (FileItem)i.next();
                    if ( !fi.isFormField () )  {
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        file = new File( filePath  +user+"Perfil.png") ;
                        fi.write( file ) ;
                    }
                }
            }


            if (UA.contains("Mobile")){
                response.sendRedirect("/movil/usuario.jsp");
            }else{
                response.sendRedirect("/escritorio/usuario.jsp");
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
