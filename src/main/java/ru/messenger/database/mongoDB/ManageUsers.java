package ru.messenger.database.mongoDB;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import ru.messenger.database.model.User;

public class ManageUsers {

    public static void addUser(User user) {
        MongoCollection<Document> users = MongoDB.getDB().getCollection("users");
        users.insertOne(new Document("login", user.getLogin()));
    }

}
