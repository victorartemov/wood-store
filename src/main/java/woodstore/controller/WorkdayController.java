package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.model.SoldProduct;
import woodstore.model.Workday;
import woodstore.service.impl.CategoryService;
import woodstore.service.impl.ProductService;
import woodstore.service.impl.SoldProductService;
import woodstore.service.impl.WorkdayService;
import woodstore.utils.CurrentDateUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Viktor_Artemov on 5/10/2017.
 */
@Controller
public class WorkdayController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WorkdayService workdayService;

    @Autowired
    private SoldProductService soldProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CurrentDateUtil currentDateUtil;

    @RequestMapping(value = "/workday", method = RequestMethod.GET)
    public String workday(Model model) {

        final double LINING_WIDTH = 0.096;

        Workday currentWorkDay = workdayService.today();
        model.addAttribute("currentWorkDay", currentWorkDay);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("allCategories", categories);

        if (currentWorkDay != null && currentWorkDay.isOpen()) {

            model.addAttribute("dayIsOpen", true);

            List<Category> soldCategories = new ArrayList<>();
            for (SoldProduct product : currentWorkDay.getProducts()) {
                if (!soldCategories.contains(product.getCategory())) {
                    soldCategories.add(product.getCategory());
                }
            }
            model.addAttribute("soldCategories", soldCategories);

            Map<String, List<SoldProduct>> productsByCategories = new HashMap<>();
            for (Category category : soldCategories) {
                ArrayList<SoldProduct> products = new ArrayList<>();
                for (SoldProduct product : currentWorkDay.getProducts()) {
                    if (product.getCategory().getTitle() == category.getTitle()) {
                        products.add(product);
                    }
                }
                productsByCategories.put(category.getTitle(), products);
            }
            model.addAttribute("productsByCategories", productsByCategories);

            double totalSum = 0;
            for (SoldProduct product : currentWorkDay.getProducts()) {
                if (product.getCategory().isSimple()) {
                    totalSum += product.getPrice() * product.getAmount();
                } else {
                    totalSum += product.getPrice() * LINING_WIDTH * product.getLength() * product.getAmount();
                }
            }
            DecimalFormat df = new DecimalFormat("#.0");
            model.addAttribute("totalSum", df.format(totalSum));
        } else {
            model.addAttribute("dayIsOpen", false);
        }

        return "workday";
    }

    @RequestMapping(value = "/create-new-day", method = RequestMethod.GET)
    public String createNewDay() {

        Workday newWorkday = new Workday();
        newWorkday.setDate(currentDateUtil.getCurrentDate("dd.MM.yyyy"));
        newWorkday.setOpen(true);

        workdayService.add(newWorkday);

        return "redirect:/workday";
    }

    @RequestMapping(value = "/open-the-day", method = RequestMethod.GET)
    public String openTheDay() {
        Workday currentWorkday = workdayService.today();
        if (currentWorkday != null) {
            currentWorkday.setOpen(true);
            workdayService.edit(currentWorkday);
        }

        return "redirect:/workday";
    }

    @RequestMapping(value = "/close-the-day", method = RequestMethod.GET)
    public String closeTheDay(Model model) {
        Workday currentWorkday = workdayService.today();
        if (currentWorkday != null) {
            currentWorkday.setOpen(false);
            workdayService.edit(currentWorkday);
        }

        return "redirect:/workday";
    }

    @RequestMapping(value = "/sell-the-product", method = RequestMethod.POST)
    public String createNewProduct(@RequestParam("selectProduct") String title, @RequestParam("quantity") String quantity,
                                   RedirectAttributes redirectAttributes) {

        if (title != "" && title != null) {
            Product storedProduct = productService.findByTitle(title);

            if (storedProduct == null) {
                redirectAttributes.addFlashAttribute("formInputError", "На складе больше нет данного товара");
                return "redirect:/workday";
            }
            SoldProduct soldProduct = new SoldProduct(storedProduct);

            if (quantity == "") {
                redirectAttributes.addFlashAttribute("formInputError", "Введите количество товара");
                return "redirect:/workday";
            }

            if (Integer.parseInt(quantity) == 0) {
                redirectAttributes.addFlashAttribute("formInputError", "Недопустимое количество товара");
                return "redirect:/workday";
            }

            if (Integer.parseInt(quantity) > 0 && Integer.parseInt(quantity) <= storedProduct.getAmount()) {
                soldProduct.setAmount(Integer.parseInt(quantity));
                soldProduct.setCategory(storedProduct.getCategory());

                soldProductService.add(soldProduct);

                Workday today = workdayService.today();
                today.getProducts().add(soldProduct);
                workdayService.edit(today);

                if (storedProduct.getAmount() - soldProduct.getAmount() > 0) {
                    storedProduct.setAmount(storedProduct.getAmount() - soldProduct.getAmount());
                    productService.edit(storedProduct);
                } else {
                    productService.deleteFromStore(storedProduct);
                }

                redirectAttributes.addFlashAttribute("formInputError", null);
            } else {
                if (storedProduct.getAmount() < Integer.parseInt(quantity)) {
                    redirectAttributes.addFlashAttribute("formInputError", "Превышено возможное количество товара для отправки. На складе осталось "
                            + storedProduct.getAmount() + " единиц товара");
                } else {
                    redirectAttributes.addFlashAttribute("formInputError", "Недопустимое количество товара");
                }
            }
        } else {
            redirectAttributes.addFlashAttribute("formInputError", "Не выбран товар для продажи");
        }

        return "redirect:/workday";
    }
}
