package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import woodstore.model.Category;
import woodstore.model.PossibleProduct;
import woodstore.service.impl.CategoryService;
import woodstore.service.impl.PossibleProductService;

@Controller
public class ItemsCreatingController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PossibleProductService possibleProductService;

    @RequestMapping(value = "/create-new-category", method = RequestMethod.POST)
    public String createNewCategory(@ModelAttribute("category") Category category, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            if (categoryService.findByTitle(category.getTitle()) == null) {
                categoryService.add(category);
            }
        } else {
            System.out.println(bindingResult.toString());
        }

        return "redirect:/shipment-in";
    }

    @RequestMapping(value = "/create-new-product-from-modal", method = RequestMethod.POST)
    public String createNewProductFromModal(@ModelAttribute("product") PossibleProduct product, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            if (possibleProductService.findByTitle(product.getTitle()) == null) {
                possibleProductService.add(product);
            }
        } else {
            System.out.println(bindingResult.toString());
        }

        return "redirect:/shipment-in";
    }
}
