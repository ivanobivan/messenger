package ru.messenger.database.manageDB;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import ru.messenger.database.model.User;

public class ManageUsers {

    public static void addUser(String login) {
        MongoCollection<Document> users = MongoDB.getDB().getCollection("users");
        Document document = new Document("login", login);
        users.insertOne(document);
    }

    public static User getUser(String login) {
        MongoCollection<Document> users = MongoDB.getDB().getCollection("users");
        Document query = new Document("login", login);
        Document document = users.find(query).first();
        if (document == null) return null;
        User user = new User();
        user.setId(document.get("_id").toString());
        user.setLogin(document.getString("login"));
        return user;
    }

    public static boolean findUser(String login) {
        MongoCollection<Document> users = MongoDB.getDB().getCollection("users");
        Document query = new Document("login", login);
        return users.find(query).first() != null;
    }

}
