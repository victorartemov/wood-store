package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import woodstore.model.Product;
import woodstore.model.SoldProduct;
import woodstore.service.impl.ProductService;
import woodstore.service.impl.RecievedProductService;
import woodstore.service.impl.SentProductService;
import woodstore.service.impl.SoldProductService;

/**
 * Created by Viktor_Artemov on 5/10/2017.
 */
@Controller
public class ItemsDeletingController {

    @Autowired
    private SoldProductService soldProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SentProductService sentProductService;

    @Autowired
    private RecievedProductService recievedProductService;

    @RequestMapping(value = "/delete-product-from-workday/{id}", method = RequestMethod.POST)
    public String deleteProductFromWorkday(@PathVariable("id") Long id) {

        SoldProduct soldProduct = soldProductService.findById(id);

        Product storedProduct = productService.findByTitle(soldProduct.getTitle());

        if (storedProduct != null) {
            storedProduct.setAmount(storedProduct.getAmount() + soldProduct.getAmount());
            productService.edit(storedProduct);
        } else {
            storedProduct = new Product(soldProduct);
            productService.add(storedProduct);
        }

        soldProductService.deleteFromWorkday(id);

        return "redirect:/workday";
    }

    @RequestMapping(value = "/delete-product-from-shipment-in/{id}", method = RequestMethod.POST)
    public String deleteProductFromShipmentIn(@PathVariable("id") Long id) {
        recievedProductService.deleteFromShipmentIn(id);

        return "redirect:/shipment-in";
    }

    @RequestMapping(value = "/delete-product-from-shipment-out/{id}", method = RequestMethod.POST)
    public String deleteProductFromShipmentOut(@PathVariable("id") Long id) {
        sentProductService.deleteFromShipmentOut(id);

        return "redirect:/shipment-out";
    }
}
