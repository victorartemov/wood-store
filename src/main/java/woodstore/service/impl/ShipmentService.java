package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.Shipment;
import woodstore.repository.ShipmentRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Виктор on 08.02.2017.
 */
@Service
public class ShipmentService implements ItemService<Shipment> {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public void add(Shipment item) {
        shipmentRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        shipmentRepository.delete(id);
    }

    @Override
    public void edit(Shipment item) {
        shipmentRepository.saveAndFlush(item);
    }

    @Override
    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }
}
