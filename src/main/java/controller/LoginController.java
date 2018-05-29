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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import model.*;

@WebServlet(urlPatterns = "/login", name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("login_user");
        String pass = request.getParameter("login_pass");
        RequestDispatcher rd = null;
        String UA = request.getHeader("User-Agent");
        HttpSession session = request.getSession(true);
       // Usuario.existsUser(user);
        try {
            Usuario username = Usuario.login(user,pass);
            // TODO: sustituir por listas del usuario
            // List<String> listas = Arrays.asList("Lista 1", "Lista 2", "Lista 3");
            session.setAttribute("username", username);
            Collection<Listarep> aux = username.getListarepsByIdUser();
            List<Listarep> listas = new ArrayList<>(aux);
            System.out.println(listas);
            session.setAttribute("username", username);
            session.setAttribute("misListas", listas);
            //session.setAttribute("misListas", listas);
            //session.setAttribute("misAudios", listas);
            //session.setAttribute("listasRecomendadas", listas);
            //session.setAttribute("audiosRecomendados", listas);
            if (UA.contains("Mobile")){
                response.sendRedirect("/movil/explorar.jsp");
            }else{
                response.sendRedirect("/escritorio/explorar.jsp");
            }


        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error",e.getMessage());
            if (UA.contains("Mobile")){
                response.sendRedirect("/movil/wolfsound.jsp");
            }else{
                response.sendRedirect("/escritorio/explorar.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
