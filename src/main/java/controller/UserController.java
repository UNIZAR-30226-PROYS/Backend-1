package main.java.controller;

import main.java.HibernateUtil;
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

@WebServlet(name = "UserController", urlPatterns = "/user")
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Usuario username = (Usuario) session.getAttribute("username");
        username.setConexion(); // Actualiza estado de conexion del usuario
        username.saveUser();
        String UA = request.getHeader("User-Agent");
        int idUser = Integer.parseInt(request.getParameter("id"));

        // Limpieza de valores antiguos
        session.removeAttribute("usuario");
        session.removeAttribute("listas");
        session.removeAttribute("suscripciones");
        session.removeAttribute("historial");
        session.removeAttribute("musica");
        session.removeAttribute("favoritos");
        session.removeAttribute("publico");
        session.removeAttribute("rand");

        // Extraer datos necesarios
        Usuario usuario = null;
        Collection<Suscribir> suscripciones = null;
        Collection<Listarep> listas;
        Listarep historial = null;
        Listarep mimusica = null;
        Listarep favoritos = null;
        List<Listarep> misListas = null;
        boolean publico = false;
        String rand = null;
        Session sesion = HibernateUtil.getSession();
        try {
            usuario = Usuario.getUser(idUser);
            System.out.println(usuario);

            usuario.activarSuscripciones(sesion);
            // usuario.activarListas(HibernateUtil.getSession());
            suscripciones = usuario.getSuscribirsByIdUser_0();
            listas = usuario.getListarepsByIdUser();
            misListas = new ArrayList<>(listas);
            historial = misListas.get(0);
            mimusica = misListas.get(1);
            favoritos = misListas.get(2);
            publico = usuario.getPublico();
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
        session.setAttribute("publico", publico);
        session.setAttribute("rand", Integer.toString(ThreadLocalRandom.current().nextInt(0, 100000)));
        sesion.close();
        System.out.println(usuario);
        System.out.println(misListas);
        System.out.println(suscripciones);
        System.out.println(historial);
        System.out.println(mimusica);
        System.out.println(favoritos);

        // Si el usuario no esta llamandose a si mismo
        if (username!=null && usuario !=null && usuario.getIdUser()!=username.getIdUser()){
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
        } else {
            session.setAttribute("suscrito",true);
        }

        //Cambiar si peta
        RequestDispatcher rd;

        if (UA.contains("Mobile")){
            if (username==null || (usuario !=null && usuario.getIdUser()!=username.getIdUser())) {
                //response.sendRedirect("/movil/usuarioPublico.jsp");
                rd = request.getRequestDispatcher("/movil/usuarioPublico.jsp");
            }
            else {
                //response.sendRedirect("/movil/usuario.jsp");
                rd = request.getRequestDispatcher("/movil/usuario.jsp");
            }
        }
        else{
            if (username==null || (usuario !=null && usuario.getIdUser()!=username.getIdUser())) {
                //response.sendRedirect("/movil/usuarioPublico.jsp");
                rd = request.getRequestDispatcher("/escritorio/artista.jsp");
            }
            else {
                //response.sendRedirect("/movil/usuario.jsp");
                rd = request.getRequestDispatcher("/escritorio/usuario.jsp");
            }
        }
        rd.forward(request,response);
    }
}
