package ru.messenger.database.manageSQL;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.messenger.database.entity.User;

import java.util.List;

public class ManageUser {

    public static boolean addUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User ").list();
        for (User user:users) {
            if (user.getUsername().equals(username)) {
                session.getTransaction().commit();
                session.close();
                return false;
            }
        }
        User user = new User();
        user.setUsername(username);
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public static boolean deleteUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete User where username = :param");
        query.setParameter("param", username);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }

    public static List<User> getUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> result = session.createQuery("from User ").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}
