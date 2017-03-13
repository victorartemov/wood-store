package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.repository.ProductRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Виктор on 07.02.2017.
 */
@Service
public class ProductService implements ItemService<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void add(Product item) {
        productRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public void edit(Product item) {
        productRepository.saveAndFlush(item);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByTitle(String title){
        return productRepository.findByTitle(title);
    }

    public List<Product> findByCategory(Category category){return  productRepository.findByCategory(category);}
}
