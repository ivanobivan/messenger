package ru.messenger.server.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import ru.messenger.server.service.ReceivedMessage;
import ru.messenger.server.service.SentMessage;

import java.security.Principal;

@Controller
public class MainController {

    @MessageMapping("/message")
    @SendTo("/topic/communion")
    public SentMessage communication(ReceivedMessage receivedMessage,Message<Object> message) throws Exception {
        Principal user = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
        return new SentMessage(receivedMessage.getName());
    }

    @SubscribeMapping("/chat.participants")
    public void retrieveParticipants() {
        System.out.println("LOL");
    }
}
