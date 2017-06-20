package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.SentProduct;
import woodstore.repository.SentProductRepository;
import woodstore.service.ItemService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Viktor_Artemov on 3/29/2017.
 */
@Service
public class SentProductService implements ItemService<SentProduct> {

    @Autowired
    private SentProductRepository sentProductRepository;

    @Override
    public void add(SentProduct item) {
        sentProductRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        sentProductRepository.delete(id);
    }

    @Override
    public void edit(SentProduct item) {
        sentProductRepository.delete(item);
    }

    @Override
    public List<SentProduct> findAll() {
        return sentProductRepository.findAll();
    }

    public SentProduct findByTitle(String title) {
        return sentProductRepository.findByTitle(title);
    }

    public void deleteFromShipmentOut(Long id) {
        String JDBC_DRIVER = "org.postgresql.Driver";
        String DB_URL = "jdbc:postgresql://localhost:5432/woodstore";
        String USER = "postgres";
        String PASSWORD = "Qq123456";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "DELETE FROM SHIPMENTOUT_SENTPRODUCT WHERE PRODUCTS_ID = ?; " +
                    "DELETE FROM SENTPRODUCT WHERE SENTPRODUCT.ID = ?;";
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

    public SentProduct findById(final Long id) {
        return sentProductRepository.findById(id);
    }
}
