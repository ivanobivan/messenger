package ru.messenger.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class AppTest {

    @Test
    public void testUser() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User();
        user.setId(1);
        user.setName("User_Test");
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

}
