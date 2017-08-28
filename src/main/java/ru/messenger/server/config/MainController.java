package ru.messenger.server.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ru.messenger.server.service.ChatMessage;

import java.security.Principal;

@Controller
public class MainController {

    @MessageMapping("/message")
    @SendTo("/topic/communion")
    public ChatMessage communication(ChatMessage chatMessage, Message<Object> message) throws Exception {
        Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
        String username = principal.getName();

        return new ChatMessage(username,chatMessage.getMessage(),chatMessage.getRecipient());
    }
}
