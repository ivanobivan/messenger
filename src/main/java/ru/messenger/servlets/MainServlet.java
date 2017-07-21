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

    private static HttpSession session;

    public static String getUsername() {
        return username;
    }

    public static HttpSession getSession() {
        return session;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("nick");
        //if(ManageUser.addUser(username)){
            if (session.getAttribute("nick") == null) { // TODO error
                session = request.getSession();
                session.setAttribute("nick", username);
                response.sendRedirect(request.getContextPath() + "application.jsp");
            } else {
                throw new NullPointerException();
            }
        /*}else{
            System.out.println("error");
        }*/


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
