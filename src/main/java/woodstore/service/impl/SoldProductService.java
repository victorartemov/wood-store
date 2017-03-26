package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.Category;
import woodstore.model.SoldProduct;
import woodstore.repository.ProductRepository;
import woodstore.repository.SoldProductRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Виктор on 26.03.2017.
 */
@Service
public class SoldProductService implements ItemService<SoldProduct> {

    @Autowired
    private SoldProductRepository soldProductRepository;

    @Override
    public void add(SoldProduct item) {
        soldProductRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        soldProductRepository.delete(id);
    }

    @Override
    public void edit(SoldProduct item) {
        soldProductRepository.saveAndFlush(item);
    }

    @Override
    public List<SoldProduct> findAll() {
        return soldProductRepository.findAll();
    }

    public SoldProduct findByTitle(String title){
        return soldProductRepository.findByTitle(title);
    }

    public List<SoldProduct> findByCategory(Category category){return  soldProductRepository.findByCategory(category);}
}
