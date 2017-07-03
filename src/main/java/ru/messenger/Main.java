package ru.messenger;


import com.google.gson.Gson;
import ru.messenger.client.Client;
import ru.messenger.server.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the operating mode: Server(1) or Client(2)");
        while (true) {
            int prom = scanner.nextInt();
            if (prom == 1) {
                new Server();
                break;
            } else if (prom == 2) {
                Client client = new Client();
                client.createConnection();
                break;
            } else {
                System.out.println("Unknown command");
            }
        }


    }
}
