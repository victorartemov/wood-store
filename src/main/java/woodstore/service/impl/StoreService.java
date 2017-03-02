package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.Store;
import woodstore.repository.StoreRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Виктор on 08.02.2017.
 */
@Service
public class StoreService implements ItemService<Store> {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public void add(Store item) {
        storeRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        storeRepository.delete(id);
    }

    @Override
    public void edit(Store item) {
        storeRepository.saveAndFlush(item);
    }
}
