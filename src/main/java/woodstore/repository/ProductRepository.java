package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import woodstore.model.Product;

/**
 * Created by Виктор on 07.02.2017.
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
}
