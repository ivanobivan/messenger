package ru.messenger;





import ru.messenger.client.Client;
import ru.messenger.server.Server;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the operating mode: Server(1) or Client(2)");
        while (true) {
            int prom = scanner.nextInt();
            if (prom == 1) {
                new Server();
                break;
            } else if (prom == 2) {
                new Client();
                break;
            } else {
                System.out.println("Unknown command");
            }
        }


    }
}
