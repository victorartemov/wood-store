package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import woodstore.model.Workday;

/**
 * Created by Виктор on 07.02.2017.
 */

public interface WorkdayRepository extends JpaRepository<Workday, Long> {
}
