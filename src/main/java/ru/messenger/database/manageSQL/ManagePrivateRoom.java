package ru.messenger.database.manageSQL;

import org.hibernate.Session;
import ru.messenger.database.entity.Room;

public class ManagePrivateRoom {

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
