package ru.messenger.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@ServerEndpoint(
        value = "/application.jsp/test",
        configurator = SocketConfigurator.class
)
public class ServerSocket {

    private static Map<String, Session> chatRooms = (Map<String, Session>) Collections.synchronizedMap(new LinkedHashMap());
    String userName;
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        HandshakeRequest req = (HandshakeRequest) config.getUserProperties().get("LOL");
        HttpSession httpSession = (HttpSession) req.getHttpSession();
        httpSession.getAttribute("LOL");
        chatRooms.put(userName, session);
        for (Session client : chatRooms.values()) {
            if (client != session) {
                client.getBasicRemote().sendText("newClient." + userName);
            } else {
                for (String name : chatRooms.keySet()) {
                    session.getBasicRemote().sendText("newClient." + name);
                }
            }
        }
    }

    @OnMessage
    public void onMessage(Session session, String message)  {
        chatRooms.values().forEach(client -> {
            try {
                if(client == session){
                    client.getBasicRemote().sendText("message." + userName + "." + message + ".owner");
                }else{
                    client.getBasicRemote().sendText("message." + userName + "." + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        chatRooms.remove(userName);
        for (Session client : chatRooms.values()) {
            client.getBasicRemote().sendText("removeUser." + userName);
        }
        session.close();
    }

}
