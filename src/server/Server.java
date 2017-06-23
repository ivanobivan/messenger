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

        BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

        while (in.ready()){
            System.out.println(in.readLine());
        }

        out.write(Parameters.HTML_STRING);

        out.close();
        in.close();
        socketClient.close();

    }

}
