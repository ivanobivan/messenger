package server;


import main.Parameters;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
                //System.out.println("Wait response from client...");
                Socket socket = serverSocket.accept();
                ServerConnector serverConnector = new ServerConnector(socket);
                serverConnectorList.add(serverConnector);
                serverConnector.start();
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


    private class ServerConnector extends Thread  {

        private Socket socket;
        private BufferedReader bufferedReader;
        private PrintWriter printWriter;
        private String userName = "";

        ServerConnector(Socket socket) throws IOException {
            this.socket = socket;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                userName = bufferedReader.readLine();
                System.out.println(this.userName + " come now");

                synchronized(serverConnectorList) {
                    for (ServerConnector aServerConnectorList : serverConnectorList) {
                        (aServerConnectorList).printWriter.println(userName + " : has connect");
                    }
                }

                String line = "";
                while (true) {
                    line = bufferedReader.readLine();
                    if (line.matches(".*exit.*")) {
                        break;
                    }
                    synchronized(serverConnectorList) {
                        for (ServerConnector aServerConnectorList : serverConnectorList) {
                            (aServerConnectorList).printWriter.println(userName + " : " + line);
                        }
                    }
                }

                synchronized(serverConnectorList) {
                    for (ServerConnector aServerConnectorList : serverConnectorList) {
                        (aServerConnectorList).printWriter.println(userName + " : has left us");
                    }
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
