package client;

import main.Parameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket socket;

    public Client() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Connecting...");
            socket = new Socket(Parameters.LOCAL_IP, Parameters.PORT);
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
            e.printStackTrace();
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
            System.out.println("!Threads not be aborted");
            e.printStackTrace();
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
                System.out.println("Error with messenger service");
                e.printStackTrace();
            }
        }
    }

}
