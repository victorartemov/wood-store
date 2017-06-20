package woodstore.model;

import javax.persistence.*;

import lombok.Data;

/**
 * Created by Viktor_Artemov on 3/27/2017.
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BasicProduct extends Item {
    public BasicProduct() {
    }

    public BasicProduct(Product another) {
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
