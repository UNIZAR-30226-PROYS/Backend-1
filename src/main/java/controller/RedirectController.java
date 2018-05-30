package main.java.controller;

import main.java.HibernateUtil;
import main.java.model.Cancion;
import main.java.model.Listarep;
import main.java.model.Suscribir;
import main.java.model.Usuario;

import org.hibernate.Session;
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
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "RedirectController", urlPatterns = "")
public class RedirectController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UA = request.getHeader("User-Agent");
        HttpSession session = request.getSession(true);
        Session sesion = HibernateUtil.getSession();
        try {
            List<Usuario> users = Usuario.getAllUsers();
            List<Cancion> canciones = new ArrayList<Cancion>();
            List<Listarep> listas = new ArrayList<Listarep>();
            Integer rand = ThreadLocalRandom.current().nextInt(0, users.size()-1);
            for(int i = 0;i<3;i++){
                Usuario user = users.get(rand);
                List<Cancion> tmp = Usuario.getSongsObjectOrder(user.getIdUser());
                user.activarListas(sesion);
                Collection<Listarep> tmp2 = user.getListarepsByIdUser();
                listas.addAll(tmp2);
                canciones.addAll(tmp);
                rand = (rand + 1 )%users.size();

            }
            for(int i = 0;i < listas.size();i++){
                if(listas.get(i).getNombre().equals("historial")){listas.remove(i);i--;}
                else if(listas.get(i).getNombre().equals("favoritos")){listas.remove(i);i--;}
                else if(listas.get(i).getNombre().equals("mimusica")){listas.remove(i);i--;}

            }
            session.setAttribute("listasRecomendadas", listas);
            session.setAttribute("audiosRecomendados", canciones);

        }
        catch (Exception e){session.setAttribute("error",e.getMessage());}
        sesion.close();
        if (UA.contains("Mobile")) {
            response.sendRedirect("/movil/wolfsound.jsp");
        } else {
            response.sendRedirect("/escritorio/explorar.jsp");
        }
    }
}
