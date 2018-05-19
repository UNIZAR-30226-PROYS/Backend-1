package main.java.controller;

import main.java.model.Album;
import main.java.model.Cancion;
import main.java.model.Listarep;
import main.java.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "SearchController", urlPatterns = "/search")
public class SearchController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search_input");
        //TODO: Búsqueda en la base de datos.
        RequestDispatcher rd = request.getRequestDispatcher("/movil/resultados.jsp");
        List<Listarep> listas = Listarep.searchList(search);
        List<Cancion> canciones = Cancion.searchSong(search);
        List<Usuario> usuarios = Usuario.searchUser(search);
        List<String> listasResult = new ArrayList<>();
        List<String> cancionesResult = new ArrayList<>();
        List<String> usuariosResult = new ArrayList<>();
        for (Listarep lista : listas) {
            listasResult.add(lista.getNombre());
        }
        for (Usuario usuario : usuarios) {
            usuariosResult.add(usuario.getIdUser());
        }
        for (Cancion cancion : canciones) {
            cancionesResult.add(cancion.getNombre());
        }
        request.setAttribute("listas", listasResult);
        request.setAttribute("usuarios", usuariosResult);
        request.setAttribute("canciones", cancionesResult);
        request.setAttribute("consulta", search);
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
