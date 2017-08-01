package ru.messenger.temp;

import org.junit.Test;
import ru.messenger.Convert;
import ru.messenger.database.entity.User;
import ru.messenger.database.manageSQL.HibernateUtil;
import ru.messenger.database.manageSQL.ManageUser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

public class AppTest {

    @Test
    public void testWorkBaseUser() throws IOException {
        HibernateUtil.getSessionFactory();
        for (int i = 1; i <= 10; i++) {
            ManageUser.addUser("User" + i);
        }
        List<User> list = ManageUser.getUsers();
        TreeSet<String> set = new TreeSet<>();
        set.add(list.get(2).getUsername());
        set.add(list.get(7).getUsername());
        list.get(0).setFriends(set);
        list.get(5).setSurname("Surname");
        for (User user: list) {
            user.setName("name");
            ManageUser.refreshUser(user);
        }
        User user = ManageUser.getUser(new Long(8));
        user.setEmail("user@mail.com");
        ManageUser.refreshUser(user);
        ManageUser.deleteUser(new Long(4));
    }

    @Test
    public void testConvertImage() throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("src/main/webapp/service/pic/avatar.png"));
        byte[] bytes = Convert.imageToBytes(originalImage, "png");
        BufferedImage resultImage = Convert.bytesToImage(bytes);
    }

}
