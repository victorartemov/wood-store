package woodstore.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by Viktor_Artemov on 3/27/2017.
 */
@Entity
public class ReceivedProduct extends BasicProduct {

    public ReceivedProduct() {
    }

    public ReceivedProduct(BasicProduct another) {
        this.setTitle(another.getTitle());
        this.setLength(another.getLength());
        this.setWeight(another.getWidth());
        this.setHeight(another.getHeight());
        this.setWeight(another.getWeight());
        this.setAmount(another.getAmount());
        this.setPrice(another.getPrice());
        this.setQuadrature(another.getQuadrature());
    }
}
