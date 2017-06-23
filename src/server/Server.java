package server;


import main.Parameters;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public ServerSocket getConnection() throws IOException {
      return new ServerSocket(Parameters.PORT);
    }

    public void performUserInfo(Socket socketClient) throws IOException {

        DataInputStream dataInputStream = new DataInputStream(socketClient.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socketClient.getOutputStream());

        while (dataInputStream.available() > 0){
            String fromUser = dataInputStream.readUTF();
            System.out.println(fromUser);
            dataOutputStream.writeUTF(fromUser.toUpperCase());
        }

    }
}
