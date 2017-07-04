package ru.messenger.servlets;


import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;

public class SocketServlet extends WebSocketServlet {
    private static final long serialVersionUID = 1L;
    private static ArrayList<MyMessageInbound> mmiList = new ArrayList<MyMessageInbound>();

    public StreamInbound createWebSocketInbound(String protocol) {
        return new MyMessageInbound();
    }

    private class MyMessageInbound extends MessageInbound {
        WsOutbound myoutbound;


        public void onOpen(WsOutbound outbound) {
            try {
                System.out.println("Open Client.");
                this.myoutbound = outbound;
                mmiList.add(this);
                outbound.writeTextMessage(CharBuffer.wrap("Hello!"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void onClose(int status) {
            System.out.println("Close Client.");
            mmiList.remove(this);
        }


        public void onTextMessage(CharBuffer cb) throws IOException {
            System.out.println("Accept Message : " + cb);
            for (MyMessageInbound mmib : mmiList) {
                CharBuffer buffer = CharBuffer.wrap(cb);
                mmib.myoutbound.writeTextMessage(buffer);
                mmib.myoutbound.flush();
            }
        }


        public void onBinaryMessage(ByteBuffer bb) throws IOException {
        }
    }
}
