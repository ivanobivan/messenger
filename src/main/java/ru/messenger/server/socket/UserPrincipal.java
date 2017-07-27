package ru.messenger.server.socket;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public class UserPrincipal implements Principal {
    private final HttpSession session;

    UserPrincipal(HttpSession session) {
        this.session = session;
    }

    HttpSession getSession() {
        return session;
    }

    @Override
    public String getName() {
        return "";
    }
}
