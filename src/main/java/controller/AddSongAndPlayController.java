package main.java.controller;

import main.java.HibernateUtil;
import main.java.model.Cancion;
import main.java.model.Cancioneslista;
import main.java.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/AddAndPlay", name = "AddSongAndPlayController")
public class AddSongAndPlayController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        try {
            Usuario userSession = (Usuario) session.getAttribute("username");
            String song = request.getParameter("song");
            Integer idSong = Integer.parseInt(song);

            Usuario usuario = Usuario.getUser(userSession.getIdUser());
            Cancion cancion = Cancion.getCancion(idSong);
            usuario.activarListas(HibernateUtil.getSession());
            Cancioneslista.addCancALista(usuario,"historial", cancion);
        } catch (Exception e) {

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
