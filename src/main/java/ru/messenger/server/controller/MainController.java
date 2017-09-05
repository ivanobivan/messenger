package ru.messenger.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import ru.messenger.database.manageDB.ManageUsers;
import ru.messenger.server.events.LoginEvent;
import ru.messenger.server.events.PersonsRepository;
import ru.messenger.server.redirect.ChatMessage;

import java.security.Principal;
import java.util.Collection;

@Controller
public class MainController {

    @Autowired private PersonsRepository participantRepository;

    @SubscribeMapping("/chat.persons")
    public Collection<LoginEvent> retrieveParticipants() {
        return participantRepository.getActiveSessions().values();
    }

    @MessageMapping("/chat.message")
    @SendTo("/topic/communion")
    public ChatMessage communication(@Payload ChatMessage chatMessage, Principal principal) throws Exception {
        String name = principal.getName();
        if (!ManageUsers.findUser(name)) ManageUsers.addUser(name);
        return new ChatMessage(name,chatMessage.getMessage(),chatMessage.getRecipient());
    }

}
