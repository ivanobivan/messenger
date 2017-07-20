package ru.messenger;

import ru.messenger.servlets.MainServlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/application.jsp/test")
public class ServerSocket {

    private static Map<String, Session> chatRooms = (Map<String, Session>) Collections.synchronizedMap(new LinkedHashMap());
    private String userName = MainServlet.getUsername();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        chatRooms.put(userName, session);
        for (Session client : chatRooms.values()) {
            if(client != session) {
                client.getBasicRemote().sendText("newClient." + userName);
            }
        }
        for (String name : chatRooms.keySet()) {
            session.getBasicRemote().sendText("newClient." + name);
        }

    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {

        for (Session client : chatRooms.values()) {
            if (!client.equals(session)) {
                client.getBasicRemote().sendText("message." + userName + "." + message);
            } else {
                client.getBasicRemote().sendText("message." + userName + "." + message);
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String username = userName;
        chatRooms.remove(username);

        for (Session client : chatRooms.values()) {
            client.getBasicRemote().sendText("removeUser." + username);
        }
    }

}
