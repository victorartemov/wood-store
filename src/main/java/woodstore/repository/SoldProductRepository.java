package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.model.SoldProduct;

import java.util.List;

/**
 * Created by Виктор on 26.03.2017.
 */
public interface SoldProductRepository extends JpaRepository<SoldProduct, Long> {
    SoldProduct findByTitle(String title);
    List<SoldProduct> findByCategory(Category category);
    SoldProduct findById(Long id);
}


