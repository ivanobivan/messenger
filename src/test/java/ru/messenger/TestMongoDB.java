package ru.messenger;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;
import ru.messenger.database.model.User;
import ru.messenger.database.mongoDB.ManageUsers;
import ru.messenger.database.mongoDB.MongoDB;

import java.util.ArrayList;
import java.util.List;

public class TestMongoDB {

    @Test
    public void work() {
        /*MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("chat");*/
        MongoDatabase db = MongoDB.getDB();

        MongoCollection<Document> users = db.getCollection("users");
        users.drop();

        /*List<Document> seedData = new ArrayList<Document>();
        seedData.add(new Document("login", "user1"));
        seedData.add(new Document("login", "user2"));
        seedData.add(new Document("login", "user3"));*/
        User user = new User();
        user.setLogin("user");
        ManageUsers.addUser(user);

        //users.insertMany(seedData);

        //client.close();
        MongoDB.disconnection();
    }

}
