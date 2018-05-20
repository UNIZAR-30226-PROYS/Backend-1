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
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/modify", name = "ModifyController")
public class ModifyController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UA = request.getHeader("User-Agent");
        HttpSession session = request.getSession(true);
        String userOld = (String)session.getAttribute("username");
        String userNew = request.getParameter("new_user");
        String nombreAp = request.getParameter("nombre_Ap");
        String mail = request.getParameter("mail");
        String visiS = request.getParameter("visibilidad");
        Boolean visiB = visiS.equals("Público");

        RequestDispatcher rd = null;
        try {
            Usuario.modUser(userOld,userNew,mail,nombreAp,visiB);
            session.setAttribute("username", userNew);
            if (UA.contains("Mobile")){
                response.sendRedirect("/movil/explorar.jsp");
            }else{
                response.sendRedirect("/escritorio/explorar.jsp");
            }


        } catch (Exception e) {
            e.printStackTrace();
            rd = request.getRequestDispatcher("/movil/modificarCuenta.jsp");
            request.setAttribute("error", e.getMessage());
            request.setAttribute("user", userOld);
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
