package ru.messenger.server.events;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;


public class SocketEventListener {
	
	private PersonsRepository personsRepository;
	
	private SimpMessagingTemplate messagingTemplate;
	
	private String loginDestination;
	
	private String logoutDestination;
	
	public SocketEventListener(SimpMessagingTemplate messagingTemplate, PersonsRepository participantRepository) {
		this.messagingTemplate = messagingTemplate;
		this.personsRepository = participantRepository;
	}
		
	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
		String username = headers.getUser().getName();

		LoginEvent loginEvent = new LoginEvent(username);
		messagingTemplate.convertAndSend(loginDestination, loginEvent);
        personsRepository.add(headers.getSessionId(), loginEvent);
	}
	
	@EventListener
	private void handleSessionDisconnect(SessionDisconnectEvent event) {
		
		Optional.ofNullable(personsRepository.getPersons(event.getSessionId()))
				.ifPresent(login -> {
					messagingTemplate.convertAndSend(logoutDestination, new LogoutEvent(login.getUsername()));
                    personsRepository.removePersons(event.getSessionId());
				});
	}

	public void setLoginDestination(String loginDestination) {
		this.loginDestination = loginDestination;
	}

	public void setLogoutDestination(String logoutDestination) {
		this.logoutDestination = logoutDestination;
	}
}
