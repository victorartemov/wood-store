package woodstore.model;

import javax.persistence.Entity;

import lombok.Data;

/**
 * Created by Viktor_Artemov on 3/30/2017.
 */
@Entity
@Data
public class PossibleProduct extends BasicProduct {
    public PossibleProduct() {
    }

    public PossibleProduct(Product another) {
        super(another);
    }
}
