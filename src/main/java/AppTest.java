import org.hibernate.Session;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /*@Test
    public void testApp() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AppUser user = new AppUser("firstuser");
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }*/

    @Test
    public void testBase() {
        System.out.println("Hibernate tutorial");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setBirthDate(new java.util.Date());
        contactEntity.setFirstName("Nick");
        contactEntity.setLastName("VN");

        session.save(contactEntity);
        session.getTransaction().commit();

        session.close();

    }

}
