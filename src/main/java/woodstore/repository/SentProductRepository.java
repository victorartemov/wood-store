package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.SentProduct;

/**
 * Created by Viktor_Artemov on 3/29/2017.
 */
public interface SentProductRepository extends JpaRepository<SentProduct, Long> {
    SentProduct findByTitle(String title);
}
