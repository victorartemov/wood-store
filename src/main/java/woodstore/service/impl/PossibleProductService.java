package woodstore.service.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.PossibleProduct;
import woodstore.repository.PossibleProductRepository;
import woodstore.service.ItemService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor_Artemov on 3/30/2017.
 */
@Service
public class PossibleProductService implements ItemService<PossibleProduct> {

    Session session = null;

    @Autowired
    private PossibleProductRepository possibleProductRepository;

    @Override
    public void add(PossibleProduct item) {
        possibleProductRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        possibleProductRepository.delete(id);
    }

    @Override
    public void edit(PossibleProduct item) {
        possibleProductRepository.saveAndFlush(item);
    }

    @Override
    public List<PossibleProduct> findAll() {
        return possibleProductRepository.findAll();
    }

    public PossibleProduct findByTitle(String title) {
        return possibleProductRepository.findByTitle(title);
    }

    public List<PossibleProduct> findMatchesById(Long id) {
        String JDBC_DRIVER = "org.postgresql.Driver";
        String DB_URL = "jdbc:postgresql://localhost:5432/woodstore";
        String USER = "postgres";
        String PASSWORD = "Unexpirience1";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        List<PossibleProduct> possibleProducts = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "SELECT * FROM POSSIBLEPRODUCT WHERE CATEGORY_ID = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);


            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                PossibleProduct product = new PossibleProduct();
                product.setId(resultSet.getLong("id"));
                product.setAmount(resultSet.getInt("amount"));
                product.setPrice(resultSet.getDouble("price"));
                product.setTitle(resultSet.getString("title"));

                possibleProducts.add(product);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return possibleProducts;
    }
}
