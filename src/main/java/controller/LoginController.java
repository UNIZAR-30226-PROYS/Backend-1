package controller;

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

import model.*;

@WebServlet(urlPatterns = "/login", name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("login_user");
        String pass = request.getParameter("login_pass");
        String UA = request.getHeader("User-Agent");
        HttpSession session = request.getSession(true);
        try {
            Usuario username = Usuario.login(user,pass);
            session.setAttribute("username", username);
            Collection<Listarep> aux = username.getListarepsByIdUser();
            List<Listarep> listas = new ArrayList<>(aux);

            List<Cancion> misAudios =  new ArrayList<>();
            for (Cancioneslista cancion : listas.get(1).getCancioneslistasByIdLista()) {
                misAudios.add(cancion.getCancionByIdCancion());
            }

            session.setAttribute("username", username);
            session.setAttribute("misListas", listas);
            session.setAttribute("misAudios", misAudios);

            //session.setAttribute("listasRecomendadas", listas);
            //session.setAttribute("audiosRecomendados", listas);
            if (UA.contains("Mobile")){
                response.sendRedirect("/movil/explorar.jsp");
            }else{
                response.sendRedirect("/escritorio/explorar.jsp");
            }


        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error",e.getMessage());
            RequestDispatcher rd;
            if (UA.contains("Mobile")){
                rd = request.getRequestDispatcher("/movil/wolfsound.jsp");
            }else{
                rd = request.getRequestDispatcher("/escritorio/explorar.jsp");
            }
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
