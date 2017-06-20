package woodstore.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * Created by Viktor_Artemov on 3/28/2017.
 */
@Entity
@Data
public class ShipmentIn extends Shipment {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<ReceivedProduct> products = new ArrayList<>();

    public void close() {
        super.setClosed(true);
    }
}
