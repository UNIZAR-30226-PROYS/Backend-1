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

        List<Listarep> listas = null;
        List<Cancion> canciones = null;
        List<Usuario> usuarios = null;
        if(!search.equals("")){
            System.out.println("Cadena no vacia");
            listas = Listarep.searchList(search);
            canciones = Cancion.searchSong(search);
            usuarios = Usuario.searchUser(search);
            request.setAttribute("listas", listas);
            request.setAttribute("usuarios", usuarios);
            request.setAttribute("canciones", canciones);
            request.setAttribute("consulta", search);
            if (UA.contains("Mobile")) {
                rd = request.getRequestDispatcher("/movil/resultados.jsp");
            }
            else{
                rd = request.getRequestDispatcher("/escritorio/resultados.jsp");
            }
            rd.forward(request,response);
        }else{
            System.out.println("Cadena vacia");
            if (UA.contains("Mobile")) {
                response.sendRedirect("/movil/explorar.jsp");
            }
            else{
                response.sendRedirect("/escritorio/explorar.jsp");
            }
        }
    }
}
