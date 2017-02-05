package woodstore.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Виктор on 05.02.2017.
 */
@Entity
public class Store extends Item{
    @ElementCollection
    Collection<Product> storedProducts = new ArrayList<>();
}
