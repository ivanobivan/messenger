package ru.messenger.database.manageSQL;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.messenger.database.entity.Room;

import java.util.List;

public class ManageRoom {

    public static void saveRoom(Room room) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(room);
        session.getTransaction().commit();
        session.close();
    }

    public static Room getRoom(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Room room = session.get(Room.class, id);
        session.getTransaction().commit();
        session.close();
        return room;
    }

    public static List<Room> getRooms(String admin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Room where admin = :param");
        query.setParameter("param", admin);
        List<Room> rooms = query.list();
        session.getTransaction().commit();
        session.close();
        return rooms;
    }

    public static void refreshRoom(Room room) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.refresh(room);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteRoom(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Room room = session.get(Room.class, id);
        session.delete(room);
        session.getTransaction().commit();
        session.close();
    }

}
