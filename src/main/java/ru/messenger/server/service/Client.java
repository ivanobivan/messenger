package ru.messenger.server.service;

public class Client {

    private String userName;
    private String password;


    public Client() {

    }
    public Client(String login) {
        this.userName = login;
    }
    public Client(String login, String password) {
        this.userName = login;
        this.password = password;
    }


    public String getLogin() {
        return userName;
    }

    public void setLogin(String login) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
