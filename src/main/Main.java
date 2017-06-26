package main;

import client.Client;
import server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the operating mode: Server(1) or Client(2)");
        while (true) {
            if (scanner.nextInt() == 1) {
                new Server();
            } else if (scanner.nextInt() == 2) {
                new Client();
            } else {
                System.out.println("Unknown command");
            }
        }


    }
}
