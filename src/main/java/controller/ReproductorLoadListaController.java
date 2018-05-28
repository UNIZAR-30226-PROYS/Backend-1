package main.java.controller;

import main.java.model.Cancion;
import main.java.model.Cancioneslista;
import main.java.model.Listarep;
import main.java.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ReproductorLoadLista", name = "Reproductor_lista_v2_controller")
public class ReproductorLoadListaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        List<Cancion> canciones = (List<Cancion>) session.getAttribute("canciones");
        String text = "";
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?


        try {
            Integer i =0;
            Integer max_num_historioal =  Integer.parseInt( request.getParameter("max_num_canciones") );
            Usuario u = (Usuario) session.getAttribute("username");
            for (Cancion cancion : canciones){
                text = text+cancion.getNombre()+",";
                text = text+cancion.getIdCancion()+",";
                if ( i<max_num_historioal ) i++;
                else { break; }
            }

            response.getWriter().write(text);       // Write response body.

        } catch (Exception e) {
            response.getWriter().write("Ninguna cancion");       // Write response body.
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
