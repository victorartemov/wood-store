package woodstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.model.Profile;
import woodstore.validator.ProfileValidator;

import java.util.Locale;

/**
 * Created by Виктор on 22.01.2017.
 */
public class HibernateTest {
    public static void main(String[] args) {

        Session session = null;

        Profile admin = new Profile();
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.setName("Victor");

        Profile tester = new Profile();
        tester.setLogin("Masha");
        tester.setPassword("tester");
        tester.setName("Masha");

        Category category1 = new Category();
        category1.setCategoryTitle("Вагонка сосна");
        category1.setSimple(false);

        Category category2 = new Category();
        category2.setCategoryTitle("Вагонка липа");
        category2.setSimple(false);

        Category category3 = new Category();
        category3.setCategoryTitle("Печи дровяные");
        category3.setSimple(true);

        Product product1 = new Product();
        product1.setAmount(10);
        product1.setCategory(category1);
        product1.setLength(2);
        product1.setPrice(200);
        product1.setTitle("Вагонка сосна");

        Product product2 = new Product();
        product2.setAmount(10);
        product2.setCategory(category1);
        product2.setLength(2.1);
        product2.setPrice(200);
        product2.setTitle("Вагонка сосна");

        Product product3 = new Product();
        product3.setAmount(10);
        product3.setCategory(category1);
        product3.setLength(2.2);
        product3.setPrice(200);
        product3.setTitle("Вагонка сосна");

        Product product4 = new Product();
        product4.setAmount(10);
        product4.setCategory(category1);
        product4.setLength(2.3);
        product4.setPrice(200);
        product4.setTitle("Вагонка сосна");

        Product product5 = new Product();
        product5.setTitle("Кирасир 16");
        product5.setPrice(12000);
        product5.setAmount(3);
        product5.setCategory(category3);

        Product product6 = new Product();
        product6.setTitle("Анютка мал");
        product6.setPrice(9800);
        product6.setAmount(3);
        product6.setCategory(category3);

        Product product7 = new Product();
        product7.setTitle("Термофор 10");
        product7.setPrice(15000);
        product7.setAmount(3);
        product7.setCategory(category3);

        Product product8 = new Product();
        product8.setTitle("Greivari");
        product8.setPrice(11900);
        product8.setAmount(3);
        product8.setCategory(category3);


        try {
            //oracle db driver doesn't work properly in russian environment
            Locale.setDefault(Locale.ENGLISH);

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            //saving users
            session.save(admin);
            session.save(tester);

            //saving categories
            session.save(category1);
            session.save(category2);
            session.save(category3);

            //saving products
            session.save(product1);
            session.save(product2);
            session.save(product3);
            session.save(product4);
            session.save(product5);
            session.save(product6);
            session.save(product7);
            session.save(product8);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
