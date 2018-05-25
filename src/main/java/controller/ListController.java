package main.java.controller;

import main.java.model.Cancion;
import main.java.model.Cancioneslista;
import main.java.model.Listarep;
import main.java.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "ListController", urlPatterns = "/list")
public class ListController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UA = request.getHeader("User-Agent");
        String idlista = request.getParameter("id");
        Integer id = Integer.parseInt(idlista);
        HttpSession session = request.getSession(true);
        Usuario username = (Usuario) session.getAttribute("username");
        session.removeAttribute("canciones");
        session.removeAttribute("lista");
        session.removeAttribute("propietario");
        Listarep lista = null;
        try {
            lista = Listarep.searchList(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collection<Cancioneslista> listacanciones = lista.getCancioneslistasByIdLista();
        List<Cancion> canciones = new ArrayList<>();
        for (Cancioneslista x : listacanciones) {
            canciones.add(x.getCancionByIdCancion());
        }
        // System.out.println(canciones);

        session.setAttribute("canciones", canciones);
        session.setAttribute("lista", lista);

        // Si soy el propietario de la lista
        if(lista.getUsuarioByIdUser().getIdUser().equals(username.getIdUser())){
            session.setAttribute("propietario", true);
        }
        else {
            session.setAttribute("propietario", false);
        }

        if (UA.contains("Mobile")){
            response.sendRedirect("/movil/lista.jsp");
        }else{
            request.getRequestDispatcher("/escritorio/lista.jsp").forward(request,response);
        }
    }
}
