package woodstore.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.repository.ProductRepository;
import woodstore.service.ItemService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Виктор on 07.02.2017.
 */
@Service
public class ProductService implements ItemService<Product> {

    Session session = null;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void add(Product item) {
        productRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public void edit(Product item) {
        productRepository.saveAndFlush(item);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public void deleteFromStore(Product product) {
        String JDBC_DRIVER = "org.postgresql.Driver";
        String DB_URL = "jdbc:postgresql://localhost:5432/woodstore";
        String USER = "postgres";
        String PASSWORD = "Unexpirience1";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "DELETE FROM STORE_PRODUCT WHERE STOREDPRODUCTS_ID = ?; " +
                    "DELETE FROM PRODUCT WHERE PRODUCT.ID = ?;";
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, product.getId());
            stmt.setLong(2, product.getId());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
