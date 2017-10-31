package ru.messenger.server.service;


import org.springframework.boot.actuate.trace.InMemoryTraceRepository;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import ru.messenger.server.endpoint.WebSocketTraceEndpoint;

@Configuration
public class WebSocketTraceChannelInterceptorAutoConfiguration {

	
	private TraceRepository traceRepository = new InMemoryTraceRepository();
	
	
	@Bean
	@Description("Spring Actuator endpoint to expose WebSocket traces")
	public WebSocketTraceEndpoint websocketTraceEndpoint() {
		return new WebSocketTraceEndpoint(traceRepository);
	}
	
	@Bean
	public WebSocketTraceChannelInterceptor webSocketTraceChannelInterceptor() {
		return  new WebSocketTraceChannelInterceptor(traceRepository);
	}
}