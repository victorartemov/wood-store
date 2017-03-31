package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.PossibleProduct;

import java.util.List;

/**
 * Created by Viktor_Artemov on 3/30/2017.
 */
public interface PossibleProductRepository extends JpaRepository<PossibleProduct, Long> {
    PossibleProduct findByTitle(String title);
}
