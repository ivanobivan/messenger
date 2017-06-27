package server;


import main.Parameters;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO Реализвать доступ к закрытию сервера в настройках

public class Server {

    private final List<ServerConnector> serverConnectorList =
            Collections.synchronizedList(new ArrayList<ServerConnector>());
    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(Parameters.PORT);

            while (true) {
                System.out.println("Server start successful");
                Socket socket = serverSocket.accept();
                ServerConnector serverConnector = new ServerConnector(socket);
                serverConnectorList.add(serverConnector);
                serverConnector.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeServer();
        }
    }

    private void closeServer() {
        try {
            serverSocket.close();
            synchronized (serverConnectorList) {
                serverConnectorList.forEach(ServerConnector::closeClientCanal);
            }
        } catch (IOException e) {
            System.out.println("!Thread not be aborted!");
            e.printStackTrace();
        }
    }


    private class ServerConnector implements Runnable {

        private Socket socket;
        private BufferedReader bufferedReader;
        private PrintWriter printWriter;
        private String userName;

        ServerConnector(Socket socket) throws IOException {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                this.userName = bufferedReader.readLine();
                System.out.println(this.userName + " come now");

                synchronized (serverConnectorList) {
                    serverConnectorList.forEach(serverConnector -> {
                        printWriter.write(userName + " : connect");
                    });
                }
                String line = "";
                while (true) {
                    line = bufferedReader.readLine();
                    if (line.matches(".*exit.*")) {
                        break;
                    }
                    synchronized (serverConnectorList) {
                        String finalLine = line;
                        serverConnectorList.forEach(serverConnector -> {
                            printWriter.write(userName + " : " + finalLine);
                        });
                    }
                }
                synchronized (serverConnectorList) {
                    serverConnectorList.forEach(serverConnector -> {
                        printWriter.write(userName + " : has left us");
                    });
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeClientCanal();
            }

        }

        void closeClientCanal() {
            try {
                bufferedReader.close();
                printWriter.close();
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
