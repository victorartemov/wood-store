package woodstore.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
@Entity
public class ShipmentOut extends Shipment {

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<SentProduct> products = new ArrayList<>();

    public Collection<SentProduct> getProducts() {
        return products;
    }

    public void setProducts(Collection<SentProduct> products) {
        this.products = products;
    }

    public void close(){
        super.setClosed(true);
    }
}
