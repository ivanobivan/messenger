package ru.messenger;

import ru.messenger.servlets.MainServlet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@ServerEndpoint("/application.jsp/test")
public class ServerSocket {

    private static Map<String, Session> chatRooms = (Map<String, Session>) Collections.synchronizedMap(new LinkedHashMap());
    private String userName = MainServlet.getUsername();

    @OnOpen
    public void onOpen(Session session) throws IOException {
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
    }

}
