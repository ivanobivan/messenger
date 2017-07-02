package ru.messenger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonTransform {
    private static int PORT;
    private static String LOCAL_IP;
    private static JsonReader jsonReader;
    private static Gson gson;

    public static int getPORT() throws IOException {
        jsonReader = new JsonReader(new FileReader("src/main/resources/parameters.json"));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            if (JsonToken.NAME.equals(jsonReader.peek())) {
                if ("PORT".equals(jsonReader.nextName())) {
                    PORT = jsonReader.nextInt();
                    break;
                }
            }
        }
        return PORT;
    }

    public static String getLocalIp() throws IOException {
        jsonReader = new JsonReader(new FileReader("src/main/resources/parameters.json"));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            if (JsonToken.NAME.equals(jsonReader.peek())) {
                if ("LOCAL_IP".equals(jsonReader.nextName())) {
                    LOCAL_IP = jsonReader.nextString();
                    break;
                }jsonReader.nextString();
            }
        }
        return LOCAL_IP;
    }

    public static void putClientDataToJson(String forJSON) throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/clientInfo.json",true);
        fileWriter.write(forJSON);
        fileWriter.flush();
    }

   /* public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        LOL lol = new LOL();
        String l = gson.toJson(lol);
        System.out.println(l);
        FileWriter fileWriter = new FileWriter("src/main/resources/clientInfo.json");
        fileWriter.write(l);
        fileWriter.flush();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/clientInfo.json"));
        String gg= bufferedReader.readLine();
        System.out.println(gg);
        LOL lol1 = gson.fromJson(gg,LOL.class);
        System.out.println(lol1.a);
        System.out.println(lol1.kk);


    }*/
}
