package woodstore.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

@Entity
@Data
public class ShipmentOut extends Shipment {

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<SentProduct> products = new ArrayList<>();

    public void close(){
        super.setClosed(true);
    }
}
