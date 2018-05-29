package main.java.controller;

import main.java.model.Listarep;
import main.java.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "CreateListController", urlPatterns = "/createList")
public class CreateListController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String UA = request.getHeader("User-Agent");
        Usuario username = (Usuario) session.getAttribute("username");
        username.setConexion(); // Actualiza estado de conexion del usuario
        username.saveUser();
        try {
            Usuario userSession = (Usuario) session.getAttribute("username");
            String listName = request.getParameter("listName");

            Usuario usuario = Usuario.getUser(userSession.getIdUser());
            Listarep lista = Listarep.addLista(usuario, listName);

            Collection<Listarep> aux = usuario.getListarepsByIdUser();
            List<Listarep> listas = new ArrayList<>(aux);
            session.setAttribute("misListas", listas);

            response.setStatus(HttpServletResponse.SC_OK); //Exito
            PrintWriter out = response.getWriter();
            out.print(lista.getIdLista());
        } catch (Exception e) {
            e.printStackTrace();
            //response.getOutputStream().print(e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN); //Error
        }

    }
}
