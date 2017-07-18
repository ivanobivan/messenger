package ru.messenger;

import ru.messenger.servlets.MainServlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/application.jsp/test")
public class ServerSocket {

    private static Map<String, Session> chatRooms = (Map<String, Session>) Collections.synchronizedMap(new LinkedHashMap());
    private String nickName = MainServlet.getNickName();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        session.getUserProperties().put("username", nickName);
        chatRooms.put(nickName, session);
        String response = "newUser|" + String.join("|", chatRooms.keySet());
        session.getBasicRemote().sendText(response);
        Iterator iterator = chatRooms.values().iterator();

        while (iterator.hasNext()) {
            Session client = (Session) iterator.next();
            if (client != session) {
                client.getBasicRemote().sendText("newUser|" + nickName);
            }
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        String[] data = message.split("\\|");
        for (String s:data) {
            System.out.println(s);
        }
        String destination = data[0];
        String messageContent = data[1];
        String sender = nickName;

        Iterator iterator = chatRooms.values().iterator();

        while (iterator.hasNext()) {
            Session client = (Session) iterator.next();
            if (!client.equals(session)) {
                client.getBasicRemote().sendText("message|" + sender + "|" + messageContent + "|all");
            }
        }

        Session client = chatRooms.get(destination);
        String response = "message|" + sender + "|" + messageContent;
        client.getBasicRemote().sendText(response);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String username = nickName;
        chatRooms.remove(username);
        Iterator var3 = chatRooms.values().iterator();

        while (var3.hasNext()) {
            Session client = (Session) var3.next();
            client.getBasicRemote().sendText("removeUser|" + username);
        }
    }

}
