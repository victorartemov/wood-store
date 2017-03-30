package woodstore.model;

import javax.persistence.*;

/**
 * Created by Viktor_Artemov on 3/27/2017.
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BasicProduct extends Item{
    public BasicProduct() {
    }

    public BasicProduct(Product another){
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

    public double getQuadrature() {
        return quadrature;
    }

    public void setQuadrature(double quadrature) {
        this.quadrature = quadrature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
