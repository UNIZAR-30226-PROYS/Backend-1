package main.java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RedirectController")
public class RedirectController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UA = request.getHeader("User-Agent");
        if (UA.contains("Mobile")){
            response.sendRedirect("/wolfsound/movil/registrarse_iniciar_sesion.jsp");
        }
        else {
            response.sendRedirect("/wolfsound/escritorio/marco.html");
        }
    }
}
