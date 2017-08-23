package ru.messenger.server.config;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.messenger.server.service.ReceivedMessage;
import ru.messenger.server.service.SentMessage;

@Controller
public class MainController {

    @MessageMapping("/message")
    @SendTo("/topic/communion")
    public SentMessage communication(ReceivedMessage message) throws Exception {
        return new SentMessage(message.getName().toUpperCase());
    }
}
