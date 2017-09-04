package ru.messenger.database.mongoDB;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    private static final MongoClient client = new MongoClient("localhost", 27017);
    private static final MongoDatabase db = client.getDatabase("chat");

    public static MongoDatabase getDB() {
        return db;
    }

    public static void disconnection() {
        client.close();
    }

}
