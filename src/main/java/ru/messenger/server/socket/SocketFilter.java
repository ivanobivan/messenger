package ru.messenger.server.socket;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.security.Principal;

@WebFilter(
        value = "/test"
)
public class SocketFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        final UserPrincipal principal = new UserPrincipal(httpRequest.getSession());
        HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(httpRequest) {
            @Override
            public Principal getUserPrincipal() {
                return principal;
            }
        };
        chain.doFilter(wrappedRequest, response);
    }

    @Override
    public void destroy() {

    }
}

