package woodstore.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Виктор on 18.01.2017.
 */
public class Workday extends Entity {

    public Workday() {}

    private Date currentDate;
    private Long workerId;
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
