package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.ShipmentOut;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
public interface ShipmentOutRepository extends JpaRepository<ShipmentOut, Long> {
}
