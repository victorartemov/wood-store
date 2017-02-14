package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import woodstore.model.Store;

/**
 * Created by Виктор on 07.02.2017.
 */

public interface StoreRepository extends JpaRepository<Store, Long> {
}
