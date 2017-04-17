package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.RecievedProduct;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
public interface RecievedProductRepository extends JpaRepository<RecievedProduct, Long> {
    RecievedProduct findById(Long id);
}
