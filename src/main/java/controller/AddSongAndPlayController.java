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
        Usuario username = (Usuario) session.getAttribute("username");
        username.setConexion(new java.sql.Timestamp(0)); // Actualiza estado de conexion del usuario
        username.saveUser();
        try {
            Usuario userSession = (Usuario) session.getAttribute("username");
            Usuario usuario = Usuario.getUser(userSession.getIdUser());
            Cancion cancion = Cancion.getCancion(Integer.parseInt(request.getParameter("song")));
            usuario.activarListas(HibernateUtil.getSession());
            Cancioneslista.addCancALista(usuario, "historial", cancion);
            cancion.incrementarReps();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            String text = cancion.getNombre() + "," + cancion.getIdCancion();
            response.getWriter().print(text);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
