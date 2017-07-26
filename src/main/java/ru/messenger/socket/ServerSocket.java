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
        value = "/application.html/test"
        //configurator = SocketConfigurator.class
)
public class ServerSocket {
    private static final String USERNAME_KEY = "username";
    private static Map<String, Session> chatRooms = (Map<String, Session>) Collections.synchronizedMap(new LinkedHashMap());

    @OnOpen
    public void onOpen(Session session) throws IOException {
        String userName = session.getRequestParameterMap().get(USERNAME_KEY).toString();
        chatRooms.put(userName, session);
        session.getUserProperties().put(USERNAME_KEY, userName);
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
        String userName = (String)session.getUserProperties().get(USERNAME_KEY);
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
        String userName = (String)session.getUserProperties().get(USERNAME_KEY);
        chatRooms.remove(userName);
        for (Session client : chatRooms.values()) {
            client.getBasicRemote().sendText("removeUser." + userName);
        }
        session.close();
    }

}
