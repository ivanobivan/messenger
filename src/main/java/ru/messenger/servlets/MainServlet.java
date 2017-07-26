package ru.messenger.servlets;

import ru.messenger.database.manageSQL.ManageUser;

import javax.servlet.RequestDispatcher;
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
        SecureJSP.setSession(request.getSession());
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("LOL",username);
        response.sendRedirect(request.getContextPath() + "application.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("isLogin").equals("true")) {
            if (request.getParameter("password").equals("admin")) {
                username = request.getParameter("username");
                response.sendRedirect(request.getContextPath() + "application.jsp");
            } else {
                request.setAttribute("response", false);
                response.sendRedirect(request.getContextPath() + "index.jsp");
            }
        } else if (request.getParameter("isLogin").equals("formReg")) {
            request.setAttribute("response", false);
            response.sendRedirect(request.getContextPath() + "index.jsp");
        } else {
            request.setAttribute("response", false);
            response.sendRedirect(request.getContextPath() + "index.jsp");
        }
    }
}
