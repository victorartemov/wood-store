package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.Shipment;

/**
 * Created by Виктор on 07.02.2017.
 */
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

}
