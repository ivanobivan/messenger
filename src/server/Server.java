package server;


import log.Logger;
import main.Parameters;

import java.io.*;
import java.net.*;


public class Server {

    public Server() {
        try{
            ServerSocket serverSocket = new ServerSocket(Parameters.PORT);

            while(true){
                Socket socket = serverSocket.accept();
                new ServerConnector(socket);
            }
        }catch (IOException e){
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
                System.out.println(this.userName + "Come now");

                while (dataInputStream.available() > 0){
                    dataOutputStream.writeUTF(dataInputStream.readUTF().toUpperCase());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
