package client;

import main.Parameters;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {

    public Socket getConnection() throws IOException {
        InetAddress inetAddress = InetAddress.getByName(Parameters.LOCAL_IP);
        return new Socket(inetAddress, Parameters.PORT);
    }

    public void stringFromClient(Socket socket) {
        //TODO use try-with-resources
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.matches(".*exit.*")) {
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
