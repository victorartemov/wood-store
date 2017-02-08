package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.Profile;

/**
 * Created by Виктор on 07.02.2017.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByName(String name);
}
