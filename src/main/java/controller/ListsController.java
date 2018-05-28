package main.java.controller;

import main.java.HibernateUtil;
import javax.servlet.RequestDispatcher;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "ListsController", urlPatterns = "/lists")
public class ListsController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.removeAttribute("listas");
        Usuario username = (Usuario) session.getAttribute("username");
        String UA = request.getHeader("User-Agent");
        int idUser = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = new Usuario();
        Collection<Listarep> listas = null;
        try {
            usuario = Usuario.getUser(idUser);
            usuario.activarListas(HibernateUtil.getSession());
            listas = usuario.getListarepsByIdUser();
            System.out.println(listas);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(usuario.getIdUser() == username.getIdUser()){
            session.setAttribute("usuario", username);
        }
        else{
            session.setAttribute("usuario", usuario);
        }
        session.setAttribute("listas", listas);

        if (UA.contains("Mobile")){
            response.sendRedirect("/movil/listas.jsp");
        }else{
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/escritorio/listas.jsp");
            rd.forward(request,response);
        }
    }
}
