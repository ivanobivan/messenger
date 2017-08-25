package ru.messenger.server.config;

import com.sun.security.auth.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //String projectId = request.getParameter("projectId");
        //request.getSession().setAttribute("projectId", projectId);
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        HttpSession session = request.getSession();
        request.getSession().setAttribute("authorisation",true);
        return super.attemptAuthentication(request, response);
    }

}
