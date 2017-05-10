package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.model.SentProduct;
import woodstore.model.ShipmentOut;
import woodstore.service.impl.CategoryService;
import woodstore.service.impl.ProductService;
import woodstore.service.impl.SentProductService;
import woodstore.service.impl.ShipmentOutService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Viktor_Artemov on 5/10/2017.
 */
@Controller
public class ShipmentOutController {

    @Autowired
    private SentProductService sentProductService;

    @Autowired
    private ShipmentOutService shipmentOutService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/shipmentout", method = RequestMethod.GET)
    public String shipmentout(Model model) {

        ShipmentOut currentShipment = shipmentOutService.getCurrentShipment();
        model.addAttribute("currentShipment", currentShipment);

        if (currentShipment != null) {

            List<Category> categories = new ArrayList<>();
            for (SentProduct product : currentShipment.getProducts()) {
                if (!categories.contains(product.getCategory())) {
                    categories.add(product.getCategory());
                }
            }
            model.addAttribute("categories", categories);


            List<Category> allCategories = categoryService.findAll();
            model.addAttribute("allCategories", allCategories);

            Map<String, List<SentProduct>> productsByCategories = new HashMap<>();
            for (Category category : categories) {
                ArrayList<SentProduct> products = new ArrayList<>();
                for (SentProduct product : currentShipment.getProducts()) {
                    if (product.getCategory().getTitle() == category.getTitle()) {
                        products.add(product);
                    }
                }
                productsByCategories.put(category.getTitle(), products);
            }
            model.addAttribute("productsByCategories", productsByCategories);

            double totalSum = 0;
            for (SentProduct product : currentShipment.getProducts()) {
                if (product.getCategory().isSimple()) {
                    totalSum += product.getPrice() * product.getAmount();
                } else {
                    totalSum += product.getPrice() * product.getAmount() * product.getLength() * 0.096;
                }
            }
            DecimalFormat df = new DecimalFormat("#.0");
            if (totalSum != 0) {
                model.addAttribute("totalSum", df.format(totalSum));
            } else model.addAttribute("totalSum", 0);
        }

        return "shipmentout";
    }

    @RequestMapping(value = "/createNewShipmentOut", method = RequestMethod.GET)
    public String createNewShipmentOut(Model model) {

        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        ShipmentOut currentShipment = new ShipmentOut();
        currentShipment.setDate(dateFormatter.format(currentDate));

        shipmentOutService.add(currentShipment);

        return "redirect:/shipmentout";
    }

    @RequestMapping(value = "/closeCurrentShipmentOut", method = RequestMethod.GET)
    public String closeCurrentShipmentOut(Model model) {

        //пополняем склад всеми товарами из прихода
        ShipmentOut currentShipment = shipmentOutService.getCurrentShipment();

        Collection<SentProduct> sentProducts = currentShipment.getProducts();

        //если что-то отправили - закрываем и сохраняем, если ничего нет - удаляем
        if (sentProducts.size() != 0) {
            for (SentProduct product : sentProducts) {
                Product storedProduct = productService.findByTitle(product.getTitle());

                if (storedProduct.getAmount() - product.getAmount() > 0) {
                    storedProduct.setAmount(storedProduct.getAmount() - product.getAmount());
                    productService.edit(storedProduct);
                } else {
                    productService.deleteFromStore(storedProduct);
                }
            }
            //закрываем приход
            currentShipment.close();
            shipmentOutService.edit(currentShipment);
        } else {
            shipmentOutService.delete(currentShipment.getId());
        }

        return "redirect:/shipmentout";
    }

    @RequestMapping(value = "/createNewShipmentOutProduct", method = RequestMethod.POST)
    public String createNewShipmentOutProduct(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ShipmentOut currentShipment = shipmentOutService.getCurrentShipment();

        String title = request.getParameter("selectProduct");
        String quantity = request.getParameter("quantity");

        if (title != null && title != "") {
            Product storedProduct = productService.findByTitle(title);
            if (storedProduct == null) {
                redirectAttributes.addFlashAttribute("formInputError", "На складе нет данного товара");
                return "redirect:/shipmentout";
            }
            SentProduct sentProduct = new SentProduct(storedProduct);

            if (quantity == "") {
                redirectAttributes.addFlashAttribute("formInputError", "Введите количество товара");
                return "redirect:/shipmentout";
            }

            //check if we already have this product to ship out and its quantity(multiple rows may exist)
            int summaryCountOfAlreadySentProducts = 0;
            for (SentProduct product : currentShipment.getProducts()) {
                if (product.getTitle().equals(sentProduct.getTitle())) {
                    summaryCountOfAlreadySentProducts += product.getAmount();
                }
            }

            if (Integer.parseInt(quantity) == 0) {
                redirectAttributes.addFlashAttribute("formInputError", "Введите количество товара");
                return "redirect:/shipmentout";
            }

            if ((Integer.parseInt(quantity) > 0) && (storedProduct.getAmount() >= (Integer.parseInt(quantity) + summaryCountOfAlreadySentProducts))) {
                sentProduct.setAmount(Integer.parseInt(quantity));
                sentProduct.setCategory(storedProduct.getCategory());

                sentProductService.add(sentProduct);

                currentShipment.getProducts().add(sentProduct);
                shipmentOutService.edit(currentShipment);

                redirectAttributes.addFlashAttribute("formInputError", null);
            } else {
                if (storedProduct.getAmount() < (Integer.parseInt(quantity) + summaryCountOfAlreadySentProducts)) {
                    redirectAttributes.addFlashAttribute("formInputError", "Превышено возможное количество товара для отправки. На складе осталось "
                            + (sentProduct.getAmount() - summaryCountOfAlreadySentProducts) + " единиц товара");
                } else {
                    redirectAttributes.addFlashAttribute("formInputError", "Недопустимое количество товара");
                }
            }
        } else {
            redirectAttributes.addFlashAttribute("formInputError", "Не выбран товар для отправки");
        }

        return "redirect:/shipmentout";
    }
}
