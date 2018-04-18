package main.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterController")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String confirm = request.getParameter("confirm_pass");
        String email = request.getParameter("email");
        RequestDispatcher rd = null;
        if (user.equals("Miguel") && pass.equals("1234")){
            rd = request.getRequestDispatcher("/explorar.html");
            request.setAttribute("user", "Miguel");
        }
        else{
            rd = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("error", "Username taken");
        }
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
