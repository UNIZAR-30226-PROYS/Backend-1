package main.java.controller;

import java.util.*;

import main.java.HibernateUtil;
import main.java.model.Usuario;
import main.java.model.Cancion;
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



@WebServlet(urlPatterns = "/uploadSong", name = "UploadSongController")
public class UploadSongController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario User = (Usuario)session.getAttribute("username");
        String UA = request.getHeader("User-Agent");
        try {
            //Modificaciones en la base de datos.
            String nombre = request.getParameter("nombre");
            String genero = request.getParameter("album");
            String album  = "error";

            //Almacenamiento de ficheros.
            File file ;
            int maxFileSize = 10000 * 1024;
            int maxMemSize = 10000 * 1024;
            String filePath ="/contenido/imagenes/usuarios/";

            String contentType = request.getContentType();

            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(maxMemSize);
                factory.setRepository(new File("/contenido"));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax( maxFileSize );
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                //Encontramos campos.
                while ( i.hasNext () )
                {
                    FileItem fi = (FileItem)i.next();
                    String fieldName = fi.getFieldName();
                    if ( fi.isFormField () )  { //Recuperamos parametros.
                        if(fieldName.equals("nombre"))nombre = fi.getString();
                        if(fieldName.equals("genero"))genero = fi.getString();
                        if(fieldName.equals("album"))album = fi.getString();
                    }
                }
                Cancion cancion = Cancion.addCancion(nombre,genero,User);
                i = fileItems.iterator();
                //Subimos ficheros.
                while ( i.hasNext () )
                {
                    FileItem fi = (FileItem)i.next();
                    String fieldName = fi.getFieldName();
                    if ( !fi.isFormField () )  { //Almacenamos ficheros.
                        if(fieldName.equals("cancion")) {
                            filePath ="/contenido/canciones/";
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();
                            file = new File(filePath +Integer.toString(cancion.getIdCancion()) + ".mp3");
                            fi.write(file);
                        }
                        else{
                            filePath ="/contenido/imagenes/canciones/";
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();
                            file = new File(filePath +Integer.toString(cancion.getIdCancion()) + ".png");
                            fi.write(file);
                        }
                    }
                }
            }

            if (UA.contains("Mobile")){
                response.sendRedirect("/movil/usuario.jsp");
            }else{
                response.sendRedirect("/escritorio/usuario.jsp");
            }

        } catch (Exception e) {
            RequestDispatcher rd;
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            if (UA.contains("Mobile")){
                rd = request.getRequestDispatcher("/movil/subirCancion.jsp");
            }else{
                rd = request.getRequestDispatcher("/escritorio/subirMusica.jsp");
            }
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}