package ru.messenger.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.messenger.server.events.LoginEvent;
import ru.messenger.server.events.PersonsRepository;
import ru.messenger.server.exeptions.MessageException;
import ru.messenger.server.redirect.ChatMessage;

import java.security.Principal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private PersonsRepository participantRepository;

    @RequestMapping({"/login","/"})
    public String login() {
        return "index.html";
    }

    @RequestMapping({"/chat"})
    public String chat() {
        return "chat.html";
    }

    @SubscribeMapping("/chat.persons")
    public Collection<LoginEvent> retrievePersons() {
        return participantRepository.getActiveSessions().values();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @MessageMapping("/chat.message")
    @SendTo("/topic/communion")
    public ChatMessage communication(@Payload ChatMessage chatMessage, Principal principal) throws Exception {
        //TODO Create normal authorisation with username and password
        //TODO Create test for check userName from principal in base and throw new Exeption
        String userName = principal.getName();
        return new ChatMessage(userName, chatMessage.getMessage(), chatMessage.getRecipient());
    }

    @RequestMapping({"/user", "/me"})
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

   /* @MessageMapping("/chat.private.{username}")
    public void filterPrivateMessage(@Payload ChatMessage message, @DestinationVariable("username") String username, Principal principal) {
        checkProfanityAndSanitize(message);

        message.setUsername(principal.getName());

        simpMessagingTemplate.convertAndSend("/user/" + username + "/exchange/amq.direct/chat.message", message);
    }*/

    @MessageExceptionHandler
    @SendToUser(value = "/exchange/amq.direct/errors", broadcast = false)
    public String handleProfanity(MessageException e) {
        return e.getMessage();
    }

   /* private void checkProfanityAndSanitize(ChatMessage message) {
        long profanityLevel = profanityFilter.getMessageProfanity(message.getMessage());
        profanity.increment(profanityLevel);
        message.setMessage(profanityFilter.filter(message.getMessage()));
    }*/

}
