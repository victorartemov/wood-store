package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.SentProduct;
import woodstore.repository.SentProductRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Viktor_Artemov on 3/29/2017.
 */
@Service
public class SentProductService implements ItemService<SentProduct> {

    @Autowired
    private SentProductRepository sentProductRepository;

    @Override
    public void add(SentProduct item) {
        sentProductRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        sentProductRepository.delete(id);
    }

    @Override
    public void edit(SentProduct item) {
        sentProductRepository.delete(item);
    }

    @Override
    public List<SentProduct> findAll() {
        return sentProductRepository.findAll();
    }

    public SentProduct findByTitle(String title) {
        return sentProductRepository.findByTitle(title);
    }
}
