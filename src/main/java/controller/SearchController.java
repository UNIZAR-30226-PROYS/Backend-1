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
import java.util.*;

@WebServlet(name = "SearchController", urlPatterns = "/search")
public class SearchController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search_input");
        String UA = request.getHeader("User-Agent");
        RequestDispatcher rd;
        if (UA.contains("Mobile")) {
            rd = request.getRequestDispatcher("/movil/resultados.jsp");
        }
        else{
            rd = request.getRequestDispatcher("/escritorio/resultados.jsp");
        }
        List<Listarep> listas = Listarep.searchList(search);
        List<Cancion> canciones = Cancion.searchSong(search);
        List<Usuario> usuarios = Usuario.searchUser(search);
        request.setAttribute("listas", listas);
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("canciones", canciones);
        request.setAttribute("consulta", search);
        rd.forward(request,response);
    }
}
