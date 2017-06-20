package woodstore.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Created by Виктор on 18.01.2017.
 */
@Entity
@Data
public class Product extends BasicProduct {

    public Product() {
    }

    public Product(PossibleProduct another) {
        this.setAmount(another.getAmount());
        this.setCategory(another.getCategory());
        this.setPrice(another.getPrice());
        this.setTitle(another.getTitle());
        this.setHeight(another.getHeight());
        this.setWeight(another.getWeight());
        this.setWidth(another.getWidth());
        this.setQuadrature(another.getQuadrature());
        this.setLength(another.getLength());
    }

    public Product(SoldProduct another) {
        this.setAmount(another.getAmount());
        this.setCategory(another.getCategory());
        this.setPrice(another.getPrice());
        this.setTitle(another.getTitle());
        this.setHeight(another.getHeight());
        this.setWeight(another.getWeight());
        this.setWidth(another.getWidth());
        this.setQuadrature(another.getQuadrature());
        this.setLength(another.getLength());
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "store_id")
    private Store store;
}
