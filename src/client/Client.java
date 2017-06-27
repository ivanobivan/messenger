package client;

import log.Logger;
import main.Parameters;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket socket;

    public Client() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Connecting...");
            socket = new Socket(Parameters.LOCAL_IP, Parameters.PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            dataInputStream = new DataInputStream(inputStream);
            dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("Enter your name");
            dataOutputStream.writeUTF(scanner.next());

            ClientMessenger clientMessenger = new ClientMessenger();
            clientMessenger.run();

            String line = "";
            while (!line.matches(".*exit.*")) {
                line = scanner.nextLine();
                dataOutputStream.writeUTF(line);
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
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        }catch (IOException e){
            System.out.println("!Threads not be aborted");
            e.printStackTrace();
        }
    }

    private class ClientMessenger implements Runnable {

        private boolean stop;


        public void setStop() {
            this.stop = true;
        }

        @Override
        public void run() {
            try {
                while (!stop) {
                    String line = dataInputStream.readUTF();
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Error with messenger service");
                e.printStackTrace();
            }
        }
    }

}
