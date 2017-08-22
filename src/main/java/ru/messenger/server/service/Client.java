package ru.messenger.server.service;

public class Client {

    private String login;
    private String password;
    private String name;


    public String getName() {
        return name;
    }

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Client() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
