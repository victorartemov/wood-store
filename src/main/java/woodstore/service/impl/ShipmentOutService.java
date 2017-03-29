package woodstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodstore.model.ShipmentOut;
import woodstore.repository.ShipmentOutRepository;
import woodstore.service.ItemService;

import java.util.List;

/**
 * Created by Viktor_Artemov on 3/29/2017.
 */
@Service
public class ShipmentOutService implements ItemService<ShipmentOut>{

    @Autowired
    private ShipmentOutRepository shipmentOutRepository;

    @Override
    public void add(ShipmentOut item) {
        shipmentOutRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Long id) {
        shipmentOutRepository.delete(id);
    }

    @Override
    public void edit(ShipmentOut item) {
        shipmentOutRepository.saveAndFlush(item);
    }

    @Override
    public List<ShipmentOut> findAll() {
        return shipmentOutRepository.findAll();
    }

    public ShipmentOut getCurrentShipment(){
        ShipmentOut shipment = null;

        List<ShipmentOut> shipments = shipmentOutRepository.findAll();
        for(ShipmentOut s: shipments){
            if(!s.isClosed()){
                shipment = s;
            }
        }

        return shipment;
    }
}
