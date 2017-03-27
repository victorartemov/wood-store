package woodstore.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Виктор on 06.02.2017.
 */
@Entity
public class Shipment extends Item {

    public Shipment() {
    }

    private Long workerId;
    private Date shipmentDate;
    private String shipFrom;
    private String shipTo;

    @OneToMany
    private Collection<Product> products = new ArrayList<>();

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

    public String getWhence() {
        return shipFrom;
    }

    public void setWhence(String whence) {
        this.shipFrom = whence;
    }

    public String getWhere() {
        return shipTo;
    }

    public void setWhere(String where) {
        this.shipTo = where;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "shipmentDate=" + shipmentDate +
                ", workerId=" + workerId +
                '}';
    }
}