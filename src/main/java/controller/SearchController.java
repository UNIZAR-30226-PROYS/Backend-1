package main.java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "SearchController", urlPatterns = "/search")
public class SearchController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search_input");
        //TODO: Búsqueda en la base de datos.
        RequestDispatcher rd = request.getRequestDispatcher("/movil/resultados.jsp");
        List<String> resultados = Arrays.asList("Resultado 1", "Resultado 2", "Resultado 3");
        request.setAttribute("resultados", resultados);
        request.setAttribute("consulta", search);
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
