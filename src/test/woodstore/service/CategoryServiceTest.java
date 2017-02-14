package woodstore.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import woodstore.config.TestDatabaseConfig;
import woodstore.model.Category;
import woodstore.service.impl.CategoryService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by Виктор on 12.02.2017.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@WebAppConfiguration
public class CategoryServiceTest {

    @Resource
    private EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    @Resource
    private CategoryService categoryService;

    @Before
    public void setUp(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testSaveCategory(){
        Category category = new Category();
        category.setCategoryTitle("Вагонка липа 2м");

        categoryService.add(category);
    }

}