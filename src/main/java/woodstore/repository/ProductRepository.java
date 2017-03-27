package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import woodstore.model.Category;
import woodstore.model.Product;

import java.util.List;

/**
 * Created by Виктор on 07.02.2017.
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
    List<Product> findByCategory(Category category);
}
