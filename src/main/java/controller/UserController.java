package main.java.controller;

import main.java.HibernateUtil;
import main.java.model.*;

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

@WebServlet(name = "UserController", urlPatterns = "/user")
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario username = (Usuario) session.getAttribute("username");
        String UA = request.getHeader("User-Agent");
        String idUser = request.getParameter("id");

        // Limpieza de valores antiguos
        session.removeAttribute("usuario");
        session.removeAttribute("listas");
        session.removeAttribute("suscripciones");
        session.removeAttribute("historial");
        session.removeAttribute("musica");
        session.removeAttribute("favoritos");
        session.removeAttribute("publico");

        // Extraer datos necesarios
        Usuario usuario = null;
        Collection<Suscribir> suscripciones = null;
        Collection<Listarep> listas;
        Listarep historial = null;
        Listarep mimusica = null;
        Listarep favoritos = null;
        List<Listarep> misListas = null;

        try {
            usuario = Usuario.getUser(idUser);
            usuario.activarSuscripciones(HibernateUtil.getSession());
            suscripciones = usuario.getSuscribirsByIdUser_0();
            listas = usuario.getListarepsByIdUser();
            misListas = new ArrayList<>(listas);
            historial = misListas.get(0);
            mimusica = misListas.get(1);
            favoritos = misListas.get(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Preparar los datos para la pagina
        session.setAttribute("usuario", usuario);
        session.setAttribute("listas", misListas);
        session.setAttribute("suscripciones", suscripciones);
        session.setAttribute("historial", historial);
        session.setAttribute("musica", mimusica);
        session.setAttribute("favoritos", favoritos);
        session.setAttribute("publico", usuario.isPublico());

        System.out.println(usuario);
        System.out.println(misListas);
        System.out.println(suscripciones);
        System.out.println(historial);
        System.out.println(mimusica);
        System.out.println(favoritos);

        // Si el usuario no esta llamandose a si mismo
        if (!usuario.getIdUser().equals(username.getIdUser())){
            // Si el usuario logueado esta suscrito al que esta buscando.
            try {
                if (Suscribir.existsSuscribir(username.getIdUser(),usuario.getIdUser())){
                    session.setAttribute("suscrito", true);
                }
                else {
                    session.setAttribute("suscrito", false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("suscrito", false);
            }
        }

        if (UA.contains("Mobile")){
            if (!usuario.getIdUser().equals(username.getIdUser())) {
                response.sendRedirect("/movil/usuarioPublico.jsp");
            }
            else {
                response.sendRedirect("/movil/usuario.jsp");
            }
        }
        else{
            if (!usuario.getIdUser().equals(username.getIdUser())) {
                response.sendRedirect("/escritorio/usuarioPublico.jsp");
            }
            else {
                response.sendRedirect("/escritorio/usuario.jsp");
            }
        }
    }
}
