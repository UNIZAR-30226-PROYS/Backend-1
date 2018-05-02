package main.java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import main.java.model.*;

@WebServlet(urlPatterns = "/login", name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: Controlador de inicio de sesion
        String user = request.getParameter("login_user");
        String pass = request.getParameter("login_pass");
        RequestDispatcher rd = null;
        Usuario.existsUser(user);
        try {
            Usuario.correctUser(user,pass);
            response.sendRedirect("/wolfsound/movil/explorar.jsp");
//            rd = request.getRequestDispatcher("/movil/explorar.jsp");
//            request.setAttribute("user", "Miguel");
        } catch (Exception e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("/movil/registrarse_iniciar_sesion.jsp");
            request.setAttribute("error", e.getMessage());
            request.setAttribute("user", user);
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
