package woodstore.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Виктор on 18.01.2017.
 */
@Entity
public class Workday extends Item {

    public Workday() {
    }

    private String date;
    private Long workerId;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
