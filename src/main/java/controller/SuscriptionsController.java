package main.java.controller;

import main.java.model.Cancion;
import main.java.model.Suscribir;
import main.java.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "SuscriptionsController", urlPatterns = "/suscriptions")
public class SuscriptionsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UA = request.getHeader("User-Agent");
        HttpSession session = request.getSession(true);
        Usuario username = (Usuario) session.getAttribute("username");
        username.setConexion(); // Actualiza estado de conexion del usuario
        username.saveUser();
        session.removeAttribute("novedades");
        List<Cancion> novedades = new ArrayList<Cancion>();
        try {
            List<Suscribir> suscripciones = Suscribir.searchSuscripciones(username.getIdUser());
            for (Suscribir sus : suscripciones) {
                List<Cancion> tmp = Usuario.getSongsObject(sus.getUsuarioByIdSuscrito().getIdUser());
                novedades.addAll(tmp);
            }
            Collections.sort(novedades);
            Collections.reverse(novedades);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        session.setAttribute("novedades", novedades);

        if (UA.contains("Mobile")) {
            response.sendRedirect("/movil/suscripciones.jsp");
        } else {
            request.getRequestDispatcher("/escritorio/artistas.jsp").forward(request, response);
        }
    }
}
