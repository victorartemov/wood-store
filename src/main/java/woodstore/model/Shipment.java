package woodstore.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Виктор on 19.01.2017.
 */
public class Shipment extends Entity {

    public Shipment() {}

    private Long workerId;
    private Date shipmentDate;
    private List<Product> products;

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
