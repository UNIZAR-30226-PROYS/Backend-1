package main.java.controller;

import main.java.model.Suscribir;
import main.java.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Suscribe", name = "SuscribeController")
public class SuscribeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario suscriptor = (Usuario)session.getAttribute("username");
        String suscrito = request.getParameter("name");
        String UA = request.getHeader("User-Agent");
        RequestDispatcher rd = null;
        try {
            Suscribir.addSuscripcion(suscriptor.getIdUser(),suscrito);
            if (UA.contains("Mobile")){
                response.sendRedirect("/movil/usuarioPublico.jsp?name="+suscrito);
            }else{
                response.sendRedirect("/escritorio/artista.jsp?name="+suscrito);
            }

        } catch (Exception e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("/movil/usuario.jsp");
            request.setAttribute("error", e.getMessage());
            rd.forward(request,response);
        }
    }
}
