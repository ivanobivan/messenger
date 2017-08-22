package ru.messenger.server.config;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import ru.messenger.server.service.Client;

public class SpringController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(Client client) throws Exception {
        return client.getName().toUpperCase();
    }
}
