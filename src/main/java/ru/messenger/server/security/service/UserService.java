package ru.messenger.server.security.service;


import ru.messenger.server.security.Client;

public interface UserService {

    Client getClient(String login);

}
