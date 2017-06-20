package woodstore.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * Created by Виктор on 18.01.2017.
 */
@Entity
@Data
public class SoldProduct extends Item {

    public SoldProduct() {
    }

    public SoldProduct(Product another){
        this.title = another.getTitle();
        this.length = another.getLength();
        this.width = another.getWidth();
        this.height = another.getHeight();
        this.weight = another.getWeight();
        this.amount = another.getAmount();
        this.price = another.getPrice();
        this.quadrature = another.getQuadrature();
    }

    private String title;
    private double length;
    private double width;
    private double height;
    private double weight;
    private int amount;
    private double price;
    private double quadrature;

    @OneToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
