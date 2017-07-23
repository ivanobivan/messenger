package ru.messenger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;

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
                }
                jsonReader.nextString();
            }
        }
        return LOCAL_IP;
    }

    public static void putClientDataToJson(String[] strings) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(new FileWriter("src/main/resources/clientInfo.json"));
        jsonWriter.beginObject();
        jsonWriter.name(strings[0]);
        jsonWriter.beginArray();
        jsonWriter.value(strings[1]);
        jsonWriter.value(strings[2]);
        jsonWriter.endArray();
        jsonWriter.endObject();
        jsonWriter.close();

    }
}
