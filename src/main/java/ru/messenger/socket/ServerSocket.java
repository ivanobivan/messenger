package ru.messenger.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(
        value = "/application.html/test",
        configurator = SocketConfigurator.class
)
public class ServerSocket {
    private static final String USERNAME_KEY = "username";
    private static Map<HttpSession, ArrayList<Session>> chatRooms = (Map<HttpSession, ArrayList<Session>>) Collections.synchronizedMap(new LinkedHashMap());

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        HttpSession  httpSession = ((UserPrincipal) session.getUserPrincipal()).getSession();
        String userName = session.getRequestParameterMap().get(USERNAME_KEY).toString();
        if (httpSession.getAttribute(USERNAME_KEY) == null) {
            httpSession.setAttribute(USERNAME_KEY, userName);
        }
        session.getUserProperties().put(USERNAME_KEY, userName);

        if (chatRooms.containsKey(httpSession)) {
            chatRooms.get(httpSession).add(session);
        } else {
            ArrayList<Session> sessions = new ArrayList<>();
            sessions.add(session);
            chatRooms.put(httpSession, sessions);
        }

        for (ArrayList<Session> list: chatRooms.values()) {
            for (Session client: list) {
                if (client != session) {
                    client.getBasicRemote().sendText("newClient." + userName);
                }
            }
        }

        for (HttpSession name: chatRooms.keySet()) {
            String currentName = name.getAttribute("username").toString();
            session.getBasicRemote().sendText("newClient." + currentName);
        }
    }

    @OnMessage
    public void onMessage(Session session, String message)  {
        String userName = (String)session.getUserProperties().get(USERNAME_KEY);
        chatRooms.values().forEach(list -> {
            for (Session client: list) {
                try {
                    if(client == session){
                        client.getBasicRemote().sendText("message." + userName + "." + message + ".owner");
                    }else{
                        client.getBasicRemote().sendText("message." + userName + "." + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String userName = (String)session.getUserProperties().get(USERNAME_KEY);
        for (ArrayList<Session> list : chatRooms.values()) {
            list.remove(session);
            for (Session client: list) {
                client.getBasicRemote().sendText("removeUser." + userName);
            }
        }
        session.close();
    }

}
