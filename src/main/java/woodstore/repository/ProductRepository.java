package woodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import woodstore.model.Category;
import woodstore.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //автоматически будет искать Product по полю title в БД
    Product findByTitle(String title);

    //автоматически будет искать список обхектов Product по категории
    List<Product> findByCategory(Category category);
}
