package ru.messenger.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AuthorisationServlet")
public class AuthorisationServlet extends HttpServlet {
    private RequestDispatcher dispatcher;
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doGet(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* if(request.getParameter("email").equals("system") && request.getParameter("password").equals("12345")){
            request.setAttribute("answer",true);
            dispatcher = request.getRequestDispatcher("index.jsp");
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
        }*/
        request.setAttribute("username","system");
        request.setAttribute("password","12345");

    }
}
