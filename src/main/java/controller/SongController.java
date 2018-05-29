package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "SongController", urlPatterns = "/song")
public class SongController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String UA = request.getHeader("User-Agent");
        String idCancion = request.getParameter("id");
        Integer id = Integer.parseInt(idCancion);
        RequestDispatcher rd;
        if (UA.contains("Mobile")) {
            rd = request.getRequestDispatcher("/movil/cancion.jsp");
        }
        else{
            rd = request.getRequestDispatcher("/escritorio/cancion.jsp");
        }
        //TODO: añadir Recuperacion de listas.
        String desc = "Error al recuperar la descripción de wikipedia.";
        Cancion cancion = null;
        List<Comentario> comentarios = null;
        try {
            cancion = (Cancion) Cancion.getCancion(id);
            comentarios = Comentario.searchComentarios(cancion);
            desc = main.java.controller.SongDescriptionController.getText(cancion.getNombre());

        }
        catch (Exception e){e.printStackTrace();}

        request.setAttribute("comentarios", comentarios);
        request.setAttribute("cancion", cancion);
        request.setAttribute("descripcion", desc);

        rd.forward(request,response);
    }
}