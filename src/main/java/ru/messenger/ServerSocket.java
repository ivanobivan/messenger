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
        session.getUserProperties().put("userName", userName);
        chatRooms.put(userName, session);
        String response = "newClient." + userName;
        session.getBasicRemote().sendText(response);

        Iterator iterator = chatRooms.values().iterator();
        while (iterator.hasNext()) {
            Session client = (Session) iterator.next();
            if (client != session) {
                client.getBasicRemote().sendText("newClient." + userName);
            }
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        String sender = userName;
        Iterator iterator = chatRooms.values().iterator();
        while (iterator.hasNext()) {
            Session client = (Session) iterator.next();
            if (!client.equals(session)) {
                client.getBasicRemote().sendText("message." + sender + "." +  message );
            }else{
                client.getBasicRemote().sendText("message." + sender + "." +  message );
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String username = userName;
        chatRooms.remove(username);
        Iterator iterator = chatRooms.values().iterator();

        while (iterator.hasNext()) {
            Session client = (Session) iterator.next();
            client.getBasicRemote().sendText("removeUser." + username);
        }
    }

}
