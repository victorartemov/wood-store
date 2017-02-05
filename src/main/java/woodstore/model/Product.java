package woodstore.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by Виктор on 18.01.2017.
 */
@Entity
public class Product extends Item {

    public Product() {
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
