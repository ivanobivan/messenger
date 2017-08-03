package ru.messenger.server.security.service;


import org.springframework.stereotype.Service;
import ru.messenger.server.security.Client;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Client getClient(String login) {
        Client user = new Client();
        user.setLogin(login);
        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        return user;
    }

}
