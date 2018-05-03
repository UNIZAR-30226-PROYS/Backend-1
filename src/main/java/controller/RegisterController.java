package main.java.controller;

import main.java.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterController", urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("register_user");
        String pass = request.getParameter("register_pass");
        String email = request.getParameter("register_email");
        RequestDispatcher rd = null;
        // TODO: Terminar registro con funciones de hibernate, esto es una prueba
        try {
            Usuario.addUser(user,pass,email);
            rd = request.getRequestDispatcher("/movil/explorar.jsp");
            response.sendRedirect("/wolfsound/movil/explorar.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("/movil/wolfsound.jsp");
            request.setAttribute("error", e.getMessage());
            request.setAttribute("registro_activo", "active");
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
