package main.java.controller;

import main.java.model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/reproductor_cont", name = "ReproductorController")
public class ReproductorController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("ultimo_instante_reproduccion", request.getParameter("momento_cancion") );
        session.setAttribute("max_instante_reproduccion", request.getParameter("momento_cancion_max") );

        try {
            /* Cambiamos el instate de reproducción del usuario */
            String user = (String) session.getAttribute("username");
            Usuario u = Usuario.getUser(user);

            String mi_momento = request.getParameter("momento_cancion");
            Double mi_momento_double = Double.parseDouble(mi_momento);

            Usuario.guardarInstante(u,mi_momento_double.intValue());

            /* response.sendRedirect("/movil/explorar.jsp");
           /*  rd.forward(request,response); */
        } catch (Exception e) {
            /* e.printStackTrace(); */
            response.sendRedirect("/movil/lista.jsp");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
