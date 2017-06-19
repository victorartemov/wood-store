package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import woodstore.model.*;
import woodstore.service.impl.*;
import woodstore.utils.CurrentDateUtil;
import woodstore.utils.DecimalFormatUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Viktor_Artemov on 5/10/2017.
 */
@Controller
public class ShipmentInController {

    @Autowired
    private ShipmentInService shipmentInService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PossibleProductService possibleProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RecievedProductService recievedProductService;

    @Autowired
    private DecimalFormatUtil decimalFormatUtil;

    @Autowired
    private CurrentDateUtil currentDateUtil;

    @RequestMapping(value = "/shipment-in", method = RequestMethod.GET)
    public String shipmentin(Model model) {

        final double LINING_WIDTH = 0.096;

        ShipmentIn currentShipment = shipmentInService.getCurrentShipment();
        model.addAttribute("currentShipment", currentShipment);

        if (currentShipment != null) {

            List<Category> categories = new ArrayList<>();
            for (ReceivedProduct product : currentShipment.getProducts()) {
                if (!categories.contains(product.getCategory())) {
                    categories.add(product.getCategory());
                }
            }
            model.addAttribute("categories", categories);


            List<Category> allCategories = categoryService.findAll();
            model.addAttribute("allCategories", allCategories);

            Map<String, List<ReceivedProduct>> productsByCategories = new HashMap<>();
            for (Category category : categories) {
                ArrayList<ReceivedProduct> products = new ArrayList<>();
                for (ReceivedProduct product : currentShipment.getProducts()) {
                    if (product.getCategory().getTitle() == category.getTitle()) {
                        products.add(product);
                    }
                }
                productsByCategories.put(category.getTitle(), products);
            }
            model.addAttribute("productsByCategories", productsByCategories);

            double totalSum = 0;
            for (ReceivedProduct product : currentShipment.getProducts()) {
                if (product.getCategory().isSimple()) {
                    totalSum += product.getPrice() * product.getAmount();
                } else {
                    totalSum += product.getPrice() * product.getAmount() * product.getLength() * LINING_WIDTH;
                }
            }

            if (totalSum != 0) {
                model.addAttribute("totalSum", decimalFormatUtil.getFormattedValue(totalSum));
            } else model.addAttribute("totalSum", 0);
        }

        return "shipment-in";
    }

    @RequestMapping(value = "/create-new-shipment-in", method = RequestMethod.GET)
    public String createNewShipmentIn(Model model) {

        ShipmentIn currentShipment = new ShipmentIn();
        currentShipment.setDate(currentDateUtil.getCurrentDate());

        shipmentInService.add(currentShipment);

        return "redirect:/shipment-in";
    }

    @RequestMapping(value = "/close-current-shipment-in", method = RequestMethod.GET)
    public String closeCurrentShipmentIn(Model model) {

        //пополняем склад всеми товарами из прихода
        ShipmentIn currentShipment = shipmentInService.getCurrentShipment();

        Collection<ReceivedProduct> receivedProducts = currentShipment.getProducts();

        if (receivedProducts.size() != 0) {
            for (ReceivedProduct product : receivedProducts) {
                Product storedProduct = null;
                if (productService.findByTitle(product.getTitle()) == null) {
                    storedProduct = new Product(possibleProductService.findByTitle(product.getTitle()));
                    storedProduct.setAmount(product.getAmount());
                    productService.add(storedProduct);
                } else {
                    storedProduct = productService.findByTitle(product.getTitle());
                    storedProduct.setAmount(storedProduct.getAmount() + product.getAmount());
                    productService.edit(storedProduct);
                }
            }
            //закрываем приход
            currentShipment.close();
            shipmentInService.edit(currentShipment);
        } else {
            shipmentInService.delete(currentShipment.getId());
        }
        return "redirect:/shipment-in";
    }

    @RequestMapping(value = "/create-new-shipment-in-product", method = RequestMethod.POST)
    public String createNewShipmentInProduct(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String title = request.getParameter("selectProduct");
        String quantity = request.getParameter("quantity");

        if (title != null && title != "") {

            BasicProduct storedProduct = null;

            if (productService.findByTitle(title) == null) {
                storedProduct = possibleProductService.findByTitle(title);
            } else {
                storedProduct = productService.findByTitle(title);
            }

            ReceivedProduct receivedProduct = new ReceivedProduct(storedProduct);

            if (quantity == "") {
                redirectAttributes.addFlashAttribute("formInputError", "Введите количество товара");
                return "redirect:/shipment-in";
            }

            if (Integer.parseInt(quantity) == 0) {
                redirectAttributes.addFlashAttribute("formInputError", "Недопустимое количество товара");
                return "redirect:/shipment-in";
            }

            if (Integer.parseInt(quantity) > 0 && quantity != "") {
                receivedProduct.setAmount(Integer.parseInt(quantity));
                receivedProduct.setCategory(storedProduct.getCategory());

                recievedProductService.add(receivedProduct);

                ShipmentIn currentShipment = shipmentInService.getCurrentShipment();

                currentShipment.getProducts().add(receivedProduct);
                shipmentInService.edit(currentShipment);

                redirectAttributes.addFlashAttribute("formInputError", null);
            } else {
                redirectAttributes.addFlashAttribute("formInputError", "Недопустимое количество товара");
            }
        } else {
            redirectAttributes.addFlashAttribute("formInputError", "Не выбран товар для прихода");
        }

        return "redirect:/shipment-in";
    }
}
