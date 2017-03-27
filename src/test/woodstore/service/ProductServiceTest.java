package woodstore.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import woodstore.config.WebConfig;
import woodstore.model.Product;

import woodstore.service.impl.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 * Created by Viktor_Artemov on 3/27/2017.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ProductServiceTest {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testSaveCategory(){
        Product product = productService.findByTitle("Вагонка сосна 2 м");
        productService.deleteFromStore(product);
    }
}
