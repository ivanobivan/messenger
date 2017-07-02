package ru.messenger.client;


import ru.messenger.CustomLogger;
import ru.messenger.JsonTransform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
    private transient BufferedReader bufferedReader;
    private transient PrintWriter printWriter;
    private transient Socket socket;
    private transient static Logger logger = Logger.getLogger(Client.class.getName());
    private String userName;
    private String currentDate;
    private String ip;

    public Client() {
        Scanner scanner = new Scanner(System.in);
        try {
            CustomLogger.getClientLogCustoms(logger);

            logger.log(Level.INFO,"Connecting...");

            socket = new Socket(JsonTransform.getLocalIp(), JsonTransform.getPORT());
            logger.log(Level.INFO,"Connecting successful");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(),true);

            this.currentDate = new SimpleDateFormat("yyyy.MM.dd : kk.mm.ss").format(new Date());
            this.ip = InetAddress.getLocalHost().getHostAddress();
            this.userName = scanner.nextLine();

            printWriter.println(userName);

            ClientMessenger clientMessenger = new ClientMessenger();
            clientMessenger.start();

            String line = "";
            while (!line.matches(".*exit.*")) {
                line = scanner.nextLine();
                printWriter.println(line);
            }
            clientMessenger.setStop();

        } catch (IOException e) {
            logger.log(Level.SEVERE,"Error on Client", e);
        } finally {
            close();
        }
    }

    private void close() {
        try{
            bufferedReader.close();
            printWriter.close();
            socket.close();
        }catch (IOException e){
            logger.log(Level.SEVERE,"!Threads not be aborted", e);
        }
    }

    private class ClientMessenger extends Thread {

        private boolean stop;


        void setStop() {
            this.stop = true;
        }

        @Override
        public void run() {
            try {
                while (!stop) {
                    String line = bufferedReader.readLine();
                    System.out.println(line);
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE,"!Threads not be aborted", e);
            }
        }
    }

}
