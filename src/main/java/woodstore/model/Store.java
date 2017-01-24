package woodstore.model;

import java.util.List;

/**
 * Created by Виктор on 18.01.2017.
 */
public class Store {
    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
