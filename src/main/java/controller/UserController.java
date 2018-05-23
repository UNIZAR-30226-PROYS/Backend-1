package main.java.controller;

import main.java.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/user")
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String UA = request.getHeader("User-Agent");
        String idUser = request.getParameter("id");
        // Integer id = Integer.parseInt(idUser);
        Usuario usuario = null;
        Collection<Suscribir> suscripciones = null;
        Collection<Listarep> listas;
        Listarep historial = null;
        Listarep mimusica = null;
        Listarep favoritos = null;
        List<Listarep> misListas = null;
        try {
            usuario = Usuario.getUser(idUser);
            if (!usuario.isPublico()){
                suscripciones = usuario.getSuscribirsByIdUser();
                listas = usuario.getListarepsByIdUser();
                misListas = new ArrayList<>(listas);
                historial = misListas.get(0);
                mimusica = misListas.get(1);
                favoritos = misListas.get(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("usuario", usuario);
        session.setAttribute("listas", misListas);
        session.setAttribute("suscripciones", suscripciones);
        session.setAttribute("historial", historial);
        session.setAttribute("musica", mimusica);
        session.setAttribute("favoritos", favoritos);
        session.setAttribute("publico", usuario.isPublico());
        try {
            if (Suscribir.existsSuscribir(usuario.getIdUser(), String.valueOf(session.getAttribute("username")))){
                session.setAttribute("suscrito", true);
            }
            else{
                session.setAttribute("suscrito", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("suscrito", false);
        }

        if (UA.contains("Mobile")){
            response.sendRedirect("/movil/usuarioPublico.jsp");
        }
        else{
            response.sendRedirect("/escritorio/usuarioPublico.jsp");
        }
    }
}
