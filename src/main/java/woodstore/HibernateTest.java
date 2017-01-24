package woodstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import woodstore.model.TestClass;

import java.util.Locale;

/**
 * Created by Виктор on 22.01.2017.
 */
public class HibernateTest {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.setId(1l);
        testClass.setUserName("Victor");

        Session session = null;

        try {

            //oracle db driver doesn't work properly in russian environment
            Locale.setDefault(Locale.ENGLISH);

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(testClass);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
