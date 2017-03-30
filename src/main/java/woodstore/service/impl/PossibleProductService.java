package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.PossibleProduct;
import woodstore.repository.PossibleProductRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Viktor_Artemov on 3/30/2017.
 */
@Service
public class PossibleProductService implements ItemService<PossibleProduct> {

    @Autowired
    private PossibleProductRepository possibleProductRepository;

    @Override
    public void add(PossibleProduct item) {
        possibleProductRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        possibleProductRepository.delete(id);
    }

    @Override
    public void edit(PossibleProduct item) {
        possibleProductRepository.saveAndFlush(item);
    }

    @Override
    public List<PossibleProduct> findAll() {
        return possibleProductRepository.findAll();
    }

    public PossibleProduct findByTitle(String title) {
        return possibleProductRepository.findByTitle(title);
    }
}
