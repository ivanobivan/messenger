package ru.messenger.servlets;


import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;


@WebServlet(name = "ServerServlet", urlPatterns = {"/ss"})
public class ServerServlet extends WebSocketServlet{
    private static final long serialVersionUID = 1L;
    private static ArrayList<WsConnection> mmiList = new ArrayList<WsConnection>();

    @Override
    protected StreamInbound createWebSocketInbound(String s, HttpServletRequest httpServletRequest) {
        return new WsConnection();
    }

    public class WsConnection extends MessageInbound {

        public ArrayBlockingQueue<WsOutbound> connections = new ArrayBlockingQueue<WsOutbound>(100);
        private WsOutbound outbound;

        @Override
        protected void onBinaryMessage(ByteBuffer byteBuffer) throws IOException {
        }

        @Override
        protected void onTextMessage(CharBuffer charBuffer) throws IOException {
            broadcast(charBuffer.toString());
        }

        @Override
        protected void onOpen(WsOutbound outbound) {
            this.outbound = outbound;
            connections.add(outbound);
        }

        @Override
        protected void onClose(int status) {
            connections.remove(this.outbound);
        }

        private void broadcast(String message) {
            for (WsOutbound connection : connections) {
                try {
                    CharBuffer buffer = CharBuffer.wrap(message);
                    connection.writeTextMessage(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
