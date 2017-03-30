package woodstore.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Виктор on 05.02.2017.
 */
@Entity
public class Store extends Item {

    private String title;

    @OneToMany(fetch = FetchType.EAGER)
    Collection<Product> storedProducts = new ArrayList<>();

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    List<PossibleProduct> possibleProducts = new ArrayList<>();

    public Collection<Product> getStoredProducts() {
        return storedProducts;
    }

    public void setStoredProducts(Collection<Product> storedProducts) {
        this.storedProducts = storedProducts;
    }

    public void addProduct(Product product) {
        storedProducts.add(product);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PossibleProduct> getPossibleProducts() {
        return possibleProducts;
    }

    public void setPossibleProducts(List<PossibleProduct> possibleProducts) {
        this.possibleProducts = possibleProducts;
    }
}
