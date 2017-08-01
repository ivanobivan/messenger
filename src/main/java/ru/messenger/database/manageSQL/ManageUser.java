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
        session.save(new User(username));
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public static boolean addUser(String username, String password) {
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
        session.save(new User(username, password));
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public static User getUser(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public static User getUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where username = :param");
        query.setParameter("param", username);
        User user = (User) query.list().get(0);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public static List<User> getUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User ").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    public static void refreshUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(user);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteUser(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete User where username = :param");
        query.setParameter("param", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
