package main.java.controller;

import main.java.model.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@WebServlet(urlPatterns = "/changeImage", name = "ChangeImageController")
public class ChangeImageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String UA = request.getHeader("User-Agent");
        Usuario username = (Usuario) session.getAttribute("username");
        username.setConexion(); // Actualiza estado de conexion del usuario
        username.saveUser();


        RequestDispatcher rd = null;
        try {
            String user = ((Usuario) session.getAttribute("username")).getUsername();
            Integer id = ((Usuario) session.getAttribute("username")).getIdUser();
            File file;
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
            String filePath = "/contenido/imagenes/usuarios/";

            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(maxMemSize);
                factory.setRepository(new File("/contenido"));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(maxFileSize);
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        file = new File(filePath + user + "Perfil.png");
                        fi.write(file);
                    }
                }
            }


            if (UA.contains("Mobile")) {
                response.sendRedirect("/user?id=" + id);
            } else {
                response.sendRedirect("/user?id=" + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("/movil/usuario.jsp");
            request.setAttribute("error", e.getMessage());
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
