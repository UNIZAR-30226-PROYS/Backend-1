package main.java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import main.java.model.*;

@WebServlet(urlPatterns = "/login", name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("login_user");
        String pass = request.getParameter("login_pass");
        RequestDispatcher rd = null;
        Usuario.existsUser(user);
        try {
            Usuario.correctUser(user,pass);
            // TODO: sustituir por listas del usuario
            List<String> listas = Arrays.asList("Lista 1", "Lista 2", "Lista 3");
            HttpSession session = request.getSession(true);
            session.setAttribute("username", user);
            session.setAttribute("misListas", listas);
            session.setAttribute("misAudios", listas);
            session.setAttribute("listasRecomendadas", listas);
            session.setAttribute("audiosRecomendados", listas);
            response.sendRedirect("/movil/explorar.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("/movil/wolfsound.jsp");
            request.setAttribute("error", e.getMessage());
            request.setAttribute("user", user);
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
