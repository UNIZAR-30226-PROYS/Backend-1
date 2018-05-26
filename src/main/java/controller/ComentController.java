package main.java.controller;

import main.java.model.Suscribir;
import main.java.model.Usuario;
import main.java.model.Cancion;
import main.java.model.Comentario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Coment", name = "ComentController")
public class ComentController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario)session.getAttribute("username");
        Integer cancionI = Integer.parseInt(request.getParameter("cancion"));
        String texto = request.getParameter("texto");

        String UA = request.getHeader("User-Agent");
        RequestDispatcher rd = null;
        try {
            if(usuario != null) {
                Cancion cancion = Cancion.getCancion(cancionI);
                Comentario.addComentario(usuario, cancion, texto);
            }

            if (UA.contains("Mobile")){
                response.sendRedirect("/song?id="+Integer.toString(cancionI));
            }else{
                response.sendRedirect("/song?id="+Integer.toString(cancionI));
            }

        } catch (Exception e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("/movil/usuario.jsp");
            request.setAttribute("error", e.getMessage());
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
