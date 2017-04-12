package woodstore.model;

import javax.persistence.*;

/**
 * Created by Виктор on 18.01.2017.
 */
@Entity
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

}
