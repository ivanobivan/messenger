package server;


import main.Parameters;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public ServerSocket getConnection() throws IOException {
        return new ServerSocket(Parameters.PORT);
    }

    public void socketInit(ServerSocket serverSocket) throws IOException {
        SocketProcessor socketProcessor = new SocketProcessor(serverSocket.accept());
        socketProcessor.readInputHeaders();
        socketProcessor.writeResponse();
    }

    private static class SocketProcessor  {

        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;

        private SocketProcessor(Socket socket) throws IOException {
            this.socket = socket;
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        }

        private void writeResponse() throws IOException {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            while (dataInputStream.available() > 0) {
                String fromUser = dataInputStream.readUTF();
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Server: YarServer/2009-09-09\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: " + fromUser.length() + "\r\n" +
                        "Connection: close\r\n\r\n";
                String result = response + fromUser;
                dataOutputStream.write(result.toUpperCase().getBytes());
                dataOutputStream.flush();
            }
        }

        private void readInputHeaders() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String string = br.readLine();
                if (string == null || string.trim().length() == 0) {
                    break;
                }
            }
        }

    }





}
