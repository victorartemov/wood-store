package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.ReceivedProduct;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
public interface RecievedProductRepository extends JpaRepository<ReceivedProduct, Long> {
    ReceivedProduct findById(Long id);
}
