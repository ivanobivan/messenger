package ru.messenger;


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ServerSocket {
    //private static final String USERNAME_KEY = "username";
    private static Map<String, Session> chatRooms =(Map<String, Session>) Collections.synchronizedMap(new LinkedHashMap());

    public ServerSocket() {
    }

    @OnOpen
    public void onOpen(Session session) throws Exception {
        Map<String, List<String>> parameter = session.getRequestParameterMap();
        List<String> list = parameter.get("username");
        String newUsername = list.get(0);
        chatRooms.put(newUsername, session);
        session.getUserProperties().put("username", newUsername);
        String response = "newUser|" + String.join("|", chatRooms.keySet());
        session.getBasicRemote().sendText(response);
        Iterator iterator = chatRooms.values().iterator();

        while(iterator.hasNext()) {
            Session client = (Session)iterator.next();
            if (client != session) {
                client.getBasicRemote().sendText("newUser|" + newUsername);
            }
        }

    }

    @OnMessage
    public void onMessage(Session session, String message) throws Exception {
        String[] data = message.split("\\|");
        String destination = data[0];
        String messageContent = data[1];
        String sender = (String)session.getUserProperties().get("username");
        if (destination.equals("all")) {
            Iterator iterator = chatRooms.values().iterator();

            while(iterator.hasNext()) {
                Session client = (Session)iterator.next();
                if (!client.equals(session)) {
                    client.getBasicRemote().sendText("message|" + sender + "|" + messageContent + "|all");
                }
            }
        } else {
            Session client = chatRooms.get(destination);
            String response = "message|" + sender + "|" + messageContent;
            client.getBasicRemote().sendText(response);
        }

    }

    @OnClose
    public void onClose(Session session) throws Exception {
        String username = (String)session.getUserProperties().get("username");
        chatRooms.remove(username);
        Iterator var3 = chatRooms.values().iterator();

        while(var3.hasNext()) {
            Session client = (Session)var3.next();
            client.getBasicRemote().sendText("removeUser|" + username);
        }

    }
}
