package main.java.controller;

import main.java.HibernateUtil;
import main.java.model.Cancion;
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
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "LogoutController", urlPatterns = "/logout")
public class LogoutController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario username = (Usuario) session.getAttribute("username");
        username.setOffline(); // Actualiza estado de conexion del usuario
        String UA = request.getHeader("User-Agent");
        session.invalidate();
        session = request.getSession(true);
        try {
            List<Usuario> users = Usuario.getAllUsers();
            List<Cancion> canciones = new ArrayList<Cancion>();
            List<Listarep> listas = new ArrayList<Listarep>();
            Integer rand = ThreadLocalRandom.current().nextInt(0, users.size()-1);
            for(int i = 0;i<3;i++){
                Usuario user = users.get(rand);
                List<Cancion> tmp = Usuario.getSongsObjectOrder(user.getIdUser());
                user.activarListas(HibernateUtil.getSession());
                Collection<Listarep> tmp2 = user.getListarepsByIdUser();
                listas.addAll(tmp2);
                canciones.addAll(tmp);
                rand = (rand + 1 )%users.size();

            }
            session.setAttribute("listasRecomendadas", listas);
            session.setAttribute("audiosRecomendados", canciones);
            session.setAttribute("error","todo bien lol"+Integer.toString(users.size()));
        }
        catch (Exception e){session.setAttribute("error",e.getMessage());}

        if (UA.contains("Mobile")){
            response.sendRedirect("/movil/explorar.jsp");
        }else{
            response.sendRedirect("/escritorio/explorar.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
