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

import woodstore.model.SentProduct;
import woodstore.model.ShipmentOut;
import woodstore.service.impl.ProductService;
import woodstore.service.impl.SentProductService;
import woodstore.service.impl.ShipmentOutService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


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

    @Autowired
    private SentProductService sentProductService;

    @Autowired
    private ShipmentOutService shipmentOutService;

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testSaveCategory() {
        List<ShipmentOut> shipmentOuts = shipmentOutService.findAll();
        for (ShipmentOut shipmentOut : shipmentOuts) {
            System.out.println(shipmentOut.getDate());
        }
    }
}
