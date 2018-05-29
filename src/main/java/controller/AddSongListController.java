package controller;

import util.HibernateUtil;
import model.Cancion;
import model.Cancioneslista;
import model.Listarep;
import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddSongListController", urlPatterns = "/addSongToList")
public class AddSongListController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String UA = request.getHeader("User-Agent");
        //String user = request.getParameter("user");
        try {
            Usuario userSession = (Usuario) session.getAttribute("username");
            int list = Integer.parseInt(request.getParameter("list"));
            String song = request.getParameter("song");
            Integer idSong = Integer.parseInt(song);

            Usuario usuario = Usuario.getUser(userSession.getIdUser());
            Cancion cancion = Cancion.getCancion(idSong);
            usuario.activarListas(HibernateUtil.getSession());
            Listarep lista = Listarep.getList(list);
            /*System.out.println(usuario);
            System.out.println(cancion);
            System.out.println(lista);
            System.out.println(lista.getCancioneslistasByIdLista());*/

            if(lista.getUsuarioByIdUser().getIdUser() == userSession.getIdUser()){
                //Si la lista es del usuario actual
                lista = Cancioneslista.addCancALista(lista,cancion);
            } else {
                throw new Exception("No es el propietario de esta lista");
            }

            response.setStatus(HttpServletResponse.SC_OK); //Exito
        } catch (Exception e) {
            e.printStackTrace();
            //response.getOutputStream().print(e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN); //Error
        }

    }
}
