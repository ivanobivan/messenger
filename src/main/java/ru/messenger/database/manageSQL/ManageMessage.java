package ru.messenger.database.manageSQL;

import org.hibernate.Session;
import ru.messenger.database.entity.Message;

import java.util.List;

public class ManageMessage {

    public static boolean saveMessage(Message message) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public static List<Message> getMessages() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Message> messages = session.createQuery("from User ").list();
        session.getTransaction().commit();
        session.close();
        return messages;
    }

}
