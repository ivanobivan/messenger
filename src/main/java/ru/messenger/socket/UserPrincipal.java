package ru.messenger.socket;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public class UserPrincipal implements Principal {
    private final HttpSession session;

    public UserPrincipal(HttpSession session) {
        this.session = session;
    }

    public HttpSession getSession() {
        return session;
    }

    @Override
    public String getName() {
        return "";
    }
}
