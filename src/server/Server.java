package server;


import log.Logger;
import main.Parameters;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Server {

    private final List<ServerConnector> serverConnectorList =
            Collections.synchronizedList(new ArrayList<ServerConnector>());
    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(Parameters.PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                serverConnectorList.add(new ServerConnector(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //TODO Реализвать доступ к закрытию сервера в настройках
            closeServer();
        }
    }

    private void closeServer() {
        try {
            serverSocket.close();
            synchronized (serverConnectorList){
                serverConnectorList.forEach(ServerConnector::closeClientCanal);
            }
        } catch (IOException e) {
            System.out.println("!Thread not be aborted!");
            e.printStackTrace();
        }
    }


    private class ServerConnector implements Runnable {

        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private String userName;

        ServerConnector(Socket socket) throws IOException {
            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                this.userName = dataInputStream.readUTF();
                System.out.println(this.userName + " come now");

                synchronized (serverConnectorList) {
                    serverConnectorList.forEach(serverConnector -> {
                        try {
                            dataOutputStream
                                    .writeUTF(userName + "connect");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
                String line = "";
                while (true) {
                    line = dataInputStream.readUTF();
                    if (line.matches(".*exit.*")) {
                        break;
                    }

                    synchronized (serverConnectorList) {
                        String finalLine = line;
                        serverConnectorList.forEach(serverConnector -> {
                            try {
                                dataOutputStream
                                        .writeUTF(userName + " : " + finalLine);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }


                synchronized (serverConnectorList) {
                    serverConnectorList.forEach(serverConnector -> {
                        try {
                            dataOutputStream.writeUTF(
                                    userName + " : has left us"
                            );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }

        }

        public void closeClientCanal() {
            try {
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
                if (serverConnectorList.size() == 0) {
                    Server.this.closeServer();
                    System.exit(0);
                }
            } catch (IOException e) {
                System.out.println("Thread not be aborted");
                e.printStackTrace();
            }
        }
    }

}
