package ru.messenger.database.manageSQL;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.messenger.database.entity.Message;

import java.util.List;

public class ManageMessage {

    public static void saveMessage(Message message) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
        session.close();
    }

    public static List<Message> getMessages(Long recipient) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Message where recipient = :param");
        query.setParameter("param", recipient);
        List<Message> messages = query.list();
        session.getTransaction().commit();
        session.close();
        return messages;
    }

}
