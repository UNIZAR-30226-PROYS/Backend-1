package controller;

import model.Cancion;
import model.Cancioneslista;
import model.Listarep;
import model.Usuario;
import util.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteSongListController", urlPatterns = "/deleteSongFromList")
public class DeleteSongListController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String UA = request.getHeader("User-Agent");
        String user = request.getParameter("user");
        String list = request.getParameter("list");
        String song = request.getParameter("song");
        Integer idSong = Integer.parseInt(song);
        try {
            Usuario usuario = Usuario.getUser(user);
            Cancion cancion = Cancion.getCancion(idSong);
            usuario.activarListas(HibernateUtil.getSession());
            Listarep lista = Usuario.getLista(usuario,list);
            System.out.println(usuario);
            System.out.println(cancion);
            System.out.println(lista);
            System.out.println(lista.getCancioneslistasByIdLista());
            Cancioneslista.borrarCancDeLista(lista,cancion);
            if (UA.contains("Mobile")){
                response.sendRedirect("/list?id="+lista.getIdLista());
            }else{
                response.sendRedirect("/list?id="+lista.getIdLista());
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher rd;
            request.setAttribute("error", e.getMessage());
            if (UA.contains("Mobile")){
                rd = request.getRequestDispatcher("/movil/lista.jsp");
            }else{
                rd = request.getRequestDispatcher("/escritorio/lista.jsp");
            }
            rd.forward(request,response);
        }

    }
}
