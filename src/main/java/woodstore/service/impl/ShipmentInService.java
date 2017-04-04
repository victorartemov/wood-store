package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.ShipmentIn;
import woodstore.repository.ShipmentInRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
@Service
public class ShipmentInService implements ItemService<ShipmentIn> {

    @Autowired
    private ShipmentInRepository shipmentInRepository;

    @Override
    public void add(ShipmentIn item) {
        shipmentInRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        shipmentInRepository.delete(id);
    }

    @Override
    public void edit(ShipmentIn item) {
        shipmentInRepository.saveAndFlush(item);
    }

    @Override
    public List<ShipmentIn> findAll() {
        return shipmentInRepository.findAll();
    }

    public ShipmentIn getCurrentShipment() {
        ShipmentIn shipment = null;

        List<ShipmentIn> shipments = shipmentInRepository.findAll();
        for (ShipmentIn s : shipments) {
            if (!s.isClosed()) {
                shipment = s;
            }
        }

        return shipment;
    }
}
