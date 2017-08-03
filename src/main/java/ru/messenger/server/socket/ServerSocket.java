package ru.messenger.server.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(
        value = "/test",
        configurator = SocketConfigurator.class
)
public class ServerSocket {
    private static final String USERNAME_KEY = "username";
    private static Map<HttpSession, ArrayList<Session>> chatRooms = (Map<HttpSession, ArrayList<Session>>) Collections.synchronizedMap(new LinkedHashMap());

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        HttpSession  httpSession = ((UserPrincipal) session.getUserPrincipal()).getSession();
        String userName;
        if (httpSession.getAttribute(USERNAME_KEY) == null) {
            userName = session.getRequestParameterMap().get(USERNAME_KEY).toString().replace('[', ' ').replace(']', ' ');
            httpSession.setAttribute(USERNAME_KEY, userName);
        } else {
            userName = httpSession.getAttribute(USERNAME_KEY).toString();
        }
        session.getUserProperties().put(USERNAME_KEY, userName);

        if (chatRooms.containsKey(httpSession)) {
            chatRooms.get(httpSession).add(session);
        } else {
            ArrayList<Session> sessions = new ArrayList<>();
            sessions.add(session);
            chatRooms.put(httpSession, sessions);
        }

        if (chatRooms.get(httpSession).size() == 1) {
            for (ArrayList<Session> list: chatRooms.values()) {
                for (Session client: list) {
                    if (!client.equals(session)) {
                        client.getBasicRemote().sendText("newClient." + userName);
                    }
                }
            }
        }

        for (HttpSession curHttpSession: chatRooms.keySet()) {
            if (!chatRooms.get(curHttpSession).isEmpty()) {
                String curName = curHttpSession.getAttribute("username").toString();
                session.getBasicRemote().sendText("newClient." + curName);
            }
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        String userName = (String) session.getUserProperties().get(USERNAME_KEY);
        if (message.equals("@disconnect")) {
            HttpSession httpSession = null;
            for (Map.Entry<HttpSession, ArrayList<Session>> entry: chatRooms.entrySet()) {
                httpSession = entry.getKey();
                if (httpSession.getAttribute(USERNAME_KEY).toString().equals(userName)) {
                    ArrayList<Session> list = entry.getValue();
                    while (list.size() != 0) {
                        Session client = list.get(0);
                        list.remove(client);
                        client.close();
                    }
                    httpSession.removeAttribute(USERNAME_KEY);
                } else {
                    for (Session client: entry.getValue()) {
                        client.getBasicRemote().sendText("removeUser." + userName);
                    }
                }
            }
            chatRooms.remove(httpSession);
        } else {
            for (Map.Entry<HttpSession, ArrayList<Session>> entry : chatRooms.entrySet()) {
                for (Session client: entry.getValue()) {
                    try {
                        if (entry.getKey().getAttribute(USERNAME_KEY).toString().equals(userName)) {
                            client.getBasicRemote().sendText("message." + userName + "." + message + ".owner");
                        } else {
                            client.getBasicRemote().sendText("message." + userName + "." + message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String userName = (String)session.getUserProperties().get(USERNAME_KEY);
        boolean empty = false;
        for (ArrayList<Session> list: chatRooms.values()) {
            if (list.contains(session)) {
                list.remove(session);
                empty = list.isEmpty();
            }
        }
        if (empty) {
            for (ArrayList<Session> list: chatRooms.values()) {
                for (Session client: list) {
                    client.getBasicRemote().sendText("removeUser." + userName);
                }
            }
        }
        session.close();
    }

}
