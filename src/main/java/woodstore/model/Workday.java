package woodstore.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Виктор on 18.01.2017.
 */
@Entity
public class Workday extends Item {

    public Workday() {
    }

    private Date currentDate;
    private Long workerId;

    @ElementCollection
    private Collection<Product> products = new ArrayList<>();

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
