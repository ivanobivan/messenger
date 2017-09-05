package ru.messenger;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;
import ru.messenger.database.model.User;
import ru.messenger.database.manageDB.ManageUsers;
import ru.messenger.database.manageDB.MongoDB;

public class TestMongoDB {

    @Test
    public void work() {
        final String userToStr = "user";

        MongoDatabase db = MongoDB.getDB();
        db.drop();

        MongoCollection<Document> users = db.getCollection("users");
        ManageUsers.addUser(userToStr);
        User user = ManageUsers.getUser(userToStr);
        System.out.println("id: " + user.getId() + " login: " + user.getLogin());

        if(!ManageUsers.findUser("master")) System.out.println("don't find");

        MongoDB.disconnection();
    }

}
