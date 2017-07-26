package ru.messenger.socket;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class SocketConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig serverEndpointConfig, HandshakeRequest request, HandshakeResponse response) {
        serverEndpointConfig.getUserProperties().put("LOL", request);
    }
}