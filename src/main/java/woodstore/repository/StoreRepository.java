package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.Store;

/**
 * Created by Виктор on 07.02.2017.
 */
public interface StoreRepository extends JpaRepository<Store, Long> {
}
