package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.Category;
import woodstore.repository.CategoryRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Виктор on 07.02.2017.
 */
@Service
public class CategoryService implements ItemService<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void add(Category item) {
        categoryRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public void edit(Category item) {
        categoryRepository.saveAndFlush(item);
    }

    public Category findByTitle(String title){
        return categoryRepository.findByTitle(title);
    }
}
