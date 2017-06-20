package woodstore.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import lombok.Data;

/**
 * Created by Виктор on 05.02.2017.
 */
@Entity
@Data
public class Store extends Item {

    private String title;

    @OneToMany(fetch = FetchType.EAGER)
    Collection<Product> storedProducts = new ArrayList<>();

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    List<PossibleProduct> possibleProducts = new ArrayList<>();

    public void addProduct(Product product) {
        storedProducts.add(product);
    }
}
