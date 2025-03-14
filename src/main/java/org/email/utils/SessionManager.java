package org.email.utils;

import org.email.model.Usuario;

public class SessionManager {
    private static SessionManager instance;
    private Usuario user;

    private SessionManager() {
        this.user = null;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void clearSession() {
        this.user = null;
    }

}
