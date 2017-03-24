package woodstore.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import woodstore.config.DataConfig;
import woodstore.config.TestDatabaseConfig;
import woodstore.config.WebConfig;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.service.impl.CategoryService;
import woodstore.service.impl.ProductService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Виктор on 12.02.2017.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class CategoryServiceTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testSaveCategory(){

        Category category = categoryService.findByTitle("Штучные товары");

        List<Product> products = productService.findByCategory(category);
        for(Product product : products){
            System.out.println(product.getTitle());
        }
    }

}