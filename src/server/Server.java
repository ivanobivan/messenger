package server;


import main.Parameters;
import main.TextLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO Реализвать доступ к закрытию сервера в настройках
//TODO Доделать номальное логгирование клиента и класс логгера
//TODO Сделать реализацию очистки логов при перезапуске и ноом запуске сервера
//TODO Сделать, чтоб логи не дублировались в консоль

public class Server {

    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private final List<ServerConnector> serverConnectorList =
            Collections.synchronizedList(new ArrayList<ServerConnector>());
    private ServerSocket serverSocket;

    public Server() {
        try {
            TextLogger.getServerLogCustoms(logger);
            serverSocket = new ServerSocket(Parameters.PORT);

            while (true) {
                logger.log(Level.INFO, "Server start successful");
                Socket socket = serverSocket.accept();
                ServerConnector serverConnector = new ServerConnector(socket);
                serverConnectorList.add(serverConnector);
                serverConnector.start();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error on server", e);
        } finally {
            closeServer();
            logger.log(Level.INFO, "Server closed successful");
        }
    }

    private void closeServer() {
        try {
            serverSocket.close();
            synchronized (serverConnectorList) {
                serverConnectorList.forEach(ServerConnector::closeClientCanal);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "!Thread not be aborted!", e);
        }
    }


    private class ServerConnector extends Thread {

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
                logger.log(Level.INFO, userName + " come now");
                synchronized (serverConnectorList) {
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
                    synchronized (serverConnectorList) {
                        for (ServerConnector aServerConnectorList : serverConnectorList) {
                            (aServerConnectorList).printWriter.println(userName + " : " + line);
                        }
                    }
                }

                synchronized (serverConnectorList) {
                    for (ServerConnector aServerConnectorList : serverConnectorList) {
                        (aServerConnectorList).printWriter.println(userName + " : has left us");
                    }
                }

            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error on server", e);
            } finally {
                closeClientCanal();
                logger.log(Level.SEVERE, "Client disconnect successful");
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
                logger.log(Level.SEVERE, "Error on server", e);
            }
        }
    }

}
