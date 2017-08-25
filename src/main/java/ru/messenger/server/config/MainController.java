package ru.messenger.server.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.messenger.server.service.Client;
import ru.messenger.server.service.ReceivedMessage;
import ru.messenger.server.service.SentMessage;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.security.Principal;
import java.util.Collection;

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
