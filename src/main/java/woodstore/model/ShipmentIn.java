package woodstore.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
@Entity
public class ShipmentIn extends Shipment {

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<RecievedProduct> products = new ArrayList<>();

    public Collection<RecievedProduct> getProducts() {
        return products;
    }

    public void setProducts(Collection<RecievedProduct> products) {
        this.products = products;
    }

    public void close(){
        super.setClosed(true);
    }
}
