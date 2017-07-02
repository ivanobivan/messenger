package ru.messenger;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class JsonTransform {
    public static int PORT;
    public static String LOCAL_IP;
    private static JsonReader jsonReader;

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
}
