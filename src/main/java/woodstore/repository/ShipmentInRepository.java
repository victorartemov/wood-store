package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.ShipmentIn;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
public interface ShipmentInRepository extends JpaRepository<ShipmentIn, Long> {
}
