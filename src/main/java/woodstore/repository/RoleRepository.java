package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.Role;

/**
 * Created by Viktor_Artemov on 3/2/2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
