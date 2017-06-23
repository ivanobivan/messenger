package main;

import client.Client;
import server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        Client client = new Client();

        ServerSocket serverSocket = server.getConnection();
        Socket clientSocket = client.getConnection();

        client.stringFromClient(clientSocket);
        server.socketInit(serverSocket);


      /*  ArrayList<String> arrayList = client.stringFromServer(clientSocket);
        arrayList.forEach(System.out::println);*/
    }
}
