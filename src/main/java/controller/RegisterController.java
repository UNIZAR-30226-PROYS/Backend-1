package main.java.controller;

import main.java.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.List;

import static main.java.BCrypt.gensalt;
import static main.java.BCrypt.hashpw;

@WebServlet(name = "RegisterController", urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("register_user");
        String pass = hashpw(request.getParameter("register_pass"),gensalt());   //Contraseña hasheada
        String email = request.getParameter("register_email");
        RequestDispatcher rd;
        String UA = request.getHeader("User-Agent");
        try {
            Usuario.addUser(user,pass,email);
            HttpSession session = request.getSession(true);
            // TODO: sustituir por listas del usuario
            List<String> listas = Arrays.asList("Lista 1", "Lista 2", "Lista 3");
            session.setAttribute("username", user);
            session.setAttribute("misListas", listas);
            session.setAttribute("misAudios", listas);
            session.setAttribute("listasRecomendadas", listas);
            session.setAttribute("audiosRecomendados", listas);
            if (UA.contains("Mobile")){
                response.sendRedirect("/movil/explorar.jsp");
            }else{
                response.sendRedirect("/escritorio/explorar.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (UA.contains("Mobile")){
                rd = request.getRequestDispatcher("/movil/wolfsound.jsp");
            }else{
                rd = request.getRequestDispatcher("/escritorio/explorar.jsp");
            }
            request.setAttribute("error", e.getMessage());
            // request.setAttribute("registro_activo", "active");
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
