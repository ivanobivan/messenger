package ru.messenger.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import ru.messenger.Convert;
import ru.messenger.entity.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AppTest {

    @Test
    public void testUser() throws IOException {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        User user = new User();
        user.setNickname("Nickname_Test");
        user.setAvatar(Convert.imageToBytes(ImageIO.read(new File("src/main/webapp/service/pic/avatar.png")), "png"));
        session.save(user);
        session.getTransaction().commit();

        User user1 = session.get(User.class, new Long(1));
        session.close();
        System.out.println(user1.getNickname());
        BufferedImage image = Convert.bytesToImage(user1.getAvatar());
    }

    @Test
    public void testConvertImage() throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("src/main/webapp/service/pic/avatar.png"));
        byte[] bytes = Convert.imageToBytes(originalImage, "png");
        BufferedImage resultImage = Convert.bytesToImage(bytes);
    }

}
