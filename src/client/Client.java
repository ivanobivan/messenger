package client;

import main.Parameters;
import main.TextLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket socket;
    private static Logger logger = Logger.getLogger(Client.class.getName());

    public Client() {
        Scanner scanner = new Scanner(System.in);
        try {
            TextLogger.getClientLogCustoms(logger);

            logger.log(Level.INFO,"Connecting...");
            System.out.println("Connecting...");

            socket = new Socket(Parameters.LOCAL_IP, Parameters.PORT);
            logger.log(Level.INFO,"Connecting successful");

            System.out.println("Connecting successful");
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(),true);

            System.out.println("Enter your name");
            printWriter.println(scanner.nextLine());

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
