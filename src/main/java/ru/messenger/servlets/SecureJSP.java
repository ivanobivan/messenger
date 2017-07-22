package ru.messenger.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.HttpJspPage;
import java.io.IOException;

public class SecureJSP extends HttpServlet implements HttpJspPage {

    private static HttpSession session;

    static void setSession(HttpSession session) {
        SecureJSP.session = session;
    }

    public static HttpSession getSession() {
        return session;
    }

    @Override
    public void _jspService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    public void jspInit() {

    }

    @Override
    public void jspDestroy() {

    }

    @Override
    public void init() throws ServletException {
        jspInit();
    }

    @Override
    public void destroy() {
        jspDestroy();
    }

    @Override
    public void service(HttpServletRequest  request, HttpServletResponse response) throws ServletException, IOException {
        if (session == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
        _jspService(request, response);
    }

}
