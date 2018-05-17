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

@WebServlet(urlPatterns = "/deleteUser", name = "DeleteUserController")
public class DeleteUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String)session.getAttribute("username");

        RequestDispatcher rd = null;
        try {
            Usuario.borrarUser(username);
            session.invalidate();
            response.sendRedirect("/movil/wolfsound.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("/movil/modificarCuenta.jsp");
            request.setAttribute("error", e.getMessage());
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
