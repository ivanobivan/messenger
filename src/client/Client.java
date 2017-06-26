package client;

import log.Logger;
import main.Parameters;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket socket;

    public Client() {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Connecting...");
            socket = new Socket(Parameters.LOCAL_IP,Parameters.PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println("Enter your name");
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getConnection() throws IOException {
        Logger.log("Start session for new Client");
        return new Socket(Parameters.LOCAL_IP, Parameters.PORT);
    }

    public void stringFromClient(Socket socket) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.matches(".*exit.*")) {
                    dataOutputStream.writeUTF(line);
                    break;
                }
                dataOutputStream.writeUTF(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> stringFromServer(Socket socket) throws IOException {
        ArrayList <String> arrayList = new ArrayList<>();
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        while (dataInputStream.available() >0 ){
           arrayList.add(dataInputStream.readUTF());
        }
        return arrayList;
    }
}
