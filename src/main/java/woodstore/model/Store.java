package woodstore.model;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Виктор on 05.02.2017.
 */
@Entity
public class Store extends Item {

    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<Product> storedProducts = new ArrayList<>();

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
}
