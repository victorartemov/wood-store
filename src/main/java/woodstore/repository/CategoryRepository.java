package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import woodstore.model.Category;

/**
 * Created by Виктор on 07.02.2017.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
}

