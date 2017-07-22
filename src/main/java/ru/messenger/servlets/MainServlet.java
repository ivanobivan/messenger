package ru.messenger.servlets;

import ru.messenger.database.manageSQL.ManageUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet
public class MainServlet extends HttpServlet {
    private static String username;

    public static String getUsername() {
        return username;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("nick");
        HttpSession session = request.getSession();
        SecureJSP.setSession(session);
        //if(ManageUser.addUser(username)){
        response.sendRedirect(request.getContextPath() + "application.jsp");
        /*}else{
            System.out.println("error");
        }*/


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
