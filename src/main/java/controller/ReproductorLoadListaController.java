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

@WebServlet(urlPatterns = "/ReproductorLoadLista", name = "ReproductorLoadListaController")
public class ReproductorLoadListaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String text = "";

        try {
            Integer i =0;
            Integer max_num =  Integer.parseInt( request.getParameter("max_num_canciones") );
            Usuario u = (Usuario) session.getAttribute("username");
            int idLista = (int) session.getAttribute("list");
            Listarep lista = Listarep.getList(idLista);
            for (Cancioneslista cancionLista : lista.getCancioneslistasByIdLista()){
                String nombre_cancion = cancionLista.getCancionByIdCancion().getNombre();
                int id_cancion = cancionLista.getCancionByIdCancion().getIdCancion();
                text = text+nombre_cancion+","+id_cancion+",";
                if ( i<max_num ) i++;
                else { break; }
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(text);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.getWriter().print("Ninguna cancion");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}