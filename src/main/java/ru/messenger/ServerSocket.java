package ru.messenger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/application.jsp/test")
public class ServerSocket {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public String onMessage(String message) {
        System.out.println(message);
        return null;
    }

    @OnOpen
    public void onOpen (Session peer) {
        System.out.println("connect");
        peers.add(peer);
    }

    @OnClose
    public void onClose (Session peer) {
        System.out.println("disconnect");
        peers.remove(peer);
    }

    @OnError
    public void onError(Error error) {

    }
}
