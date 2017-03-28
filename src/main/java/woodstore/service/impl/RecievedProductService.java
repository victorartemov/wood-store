package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.RecievedProduct;
import woodstore.repository.RecievedProductRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
@Service
public class RecievedProductService implements ItemService<RecievedProduct> {

    @Autowired
    private RecievedProductRepository recievedProductRepository;

    @Override
    public void add(RecievedProduct item) {
        recievedProductRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        recievedProductRepository.delete(id);
    }

    @Override
    public void edit(RecievedProduct item) {
        recievedProductRepository.saveAndFlush(item);
    }

    @Override
    public List<RecievedProduct> findAll() {
        return recievedProductRepository.findAll();
    }
}
