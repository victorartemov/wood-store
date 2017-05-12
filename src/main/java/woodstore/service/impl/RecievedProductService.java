package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.ReceivedProduct;
import woodstore.repository.RecievedProductRepository;
import woodstore.service.ItemService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
@Service
public class RecievedProductService implements ItemService<ReceivedProduct> {

    @Autowired
    private RecievedProductRepository recievedProductRepository;

    @Override
    public void add(ReceivedProduct item) {
        recievedProductRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        recievedProductRepository.delete(id);
    }

    @Override
    public void edit(ReceivedProduct item) {
        recievedProductRepository.saveAndFlush(item);
    }

    @Override
    public List<ReceivedProduct> findAll() {
        return recievedProductRepository.findAll();
    }

    public ReceivedProduct findById(Long id){
        return recievedProductRepository.findById(id);
    }

    public void deleteFromShipmentIn(Long id) {
        String JDBC_DRIVER = "org.postgresql.Driver";
        String DB_URL = "jdbc:postgresql://localhost:5432/woodstore";
        String USER = "postgres";
        String PASSWORD = "Unexpirience1";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String sql = "DELETE FROM SHIPMENTIN_RECIEVEDPRODUCT WHERE PRODUCTS_ID = ?; " +
                    "DELETE FROM RECIEVEDPRODUCT WHERE RECIEVEDPRODUCT.ID = ?;";
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
