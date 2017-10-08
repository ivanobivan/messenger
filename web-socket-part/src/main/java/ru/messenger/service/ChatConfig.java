package ru.messenger.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import ru.messenger.endpoint.WebSocketEndpoint;
import ru.messenger.events.PersonsRepository;
import ru.messenger.events.SocketEventListener;
import ru.messenger.endpoint.MessageMappingEndpoint;

@Configuration
@EnableConfigurationProperties(ChatProperties.class)
public class ChatConfig {

	@Autowired
	private ChatProperties chatProperties;
	
	@Bean
	@Description("Tracks user presence (join / leave) and broacasts it to all connected users")
	public SocketEventListener presenceEventListener(SimpMessagingTemplate messagingTemplate) {
		SocketEventListener presence = new SocketEventListener(messagingTemplate, personsRepository());
		//presence.setLoginDestination(chatProperties.getDestinations().getLogin());
		//presence.setLogoutDestination(chatProperties.getDestinations().getLogout());
		return presence;
	}

	@Bean
	@Description("Keeps connected users")
	public PersonsRepository personsRepository() {
		return new PersonsRepository();
	}

	@Bean
	@Description("Spring Actuator ru.messenger.endpoint to expose WebSocket stats")
	public WebSocketEndpoint websocketEndpoint(WebSocketMessageBrokerStats stats) {
		return new WebSocketEndpoint(stats);
	}
	
	@Bean
	@Description("Spring Actuator ru.messenger.endpoint to expose WebSocket message mappings")
	public MessageMappingEndpoint messageMappingEndpoint() {
		return new MessageMappingEndpoint();
	}
}
