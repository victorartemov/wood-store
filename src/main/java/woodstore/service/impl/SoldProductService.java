package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.model.SoldProduct;
import woodstore.repository.ProductRepository;
import woodstore.repository.SoldProductRepository;
import woodstore.service.ItemService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Виктор on 26.03.2017.
 */
@Service
public class SoldProductService implements ItemService<SoldProduct> {

    @Autowired
    private SoldProductRepository soldProductRepository;

    @Override
    public void add(SoldProduct item) {
        soldProductRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        soldProductRepository.delete(id);
    }

    @Override
    public void edit(SoldProduct item) {
        soldProductRepository.saveAndFlush(item);
    }

    @Override
    public List<SoldProduct> findAll() {
        return soldProductRepository.findAll();
    }

    public SoldProduct findByTitle(String title){
        return soldProductRepository.findByTitle(title);
    }

    public List<SoldProduct> findByCategory(Category category){return  soldProductRepository.findByCategory(category);}

    public SoldProduct findById(Long id){
        return soldProductRepository.findById(id);
    }

    public void deleteFromWorkday(Long id) {
        String JDBC_DRIVER = "org.postgresql.Driver";
        String DB_URL = "jdbc:postgresql://localhost:5432/woodstore";
        String USER = "postgres";
        String PASSWORD = "Unexpirience1";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "DELETE FROM WORKDAY_SOLDPRODUCT WHERE PRODUCTS_ID = ?; " +
                    "DELETE FROM SOLDPRODUCT WHERE SOLDPRODUCT.ID = ?;";
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.setLong(2, id);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
