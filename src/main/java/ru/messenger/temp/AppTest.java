package ru.messenger.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.messenger.Convert;
import ru.messenger.database.manageSQL.ManageUser;
import ru.messenger.database.entity.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppTest {

    @Test
    public void testUser() throws IOException {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setUsername("User");
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testWithoutRegistration() throws IOException {
        ManageUser.addUser("User1");
        ManageUser.addUser("User2");
        ManageUser.addUser("User3");
        ManageUser.deleteUser("User2");
        ManageUser.addUser("User2");
        ManageUser.addUser("User2");
        List<User> userList = ManageUser.getUsers();
        for (User user: userList) {
            System.out.println(user.getId() + " " + user.getUsername());
        }
        User user = ManageUser.getUserByName("User3");
        ArrayList<String> list = user.getFriends();
        list.add(ManageUser.getUserByName("User2").getUsername());
    }

    @Test
    public void testConvertImage() throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("src/main/webapp/service/pic/avatar.png"));
        byte[] bytes = Convert.imageToBytes(originalImage, "png");
        BufferedImage resultImage = Convert.bytesToImage(bytes);
    }

}
