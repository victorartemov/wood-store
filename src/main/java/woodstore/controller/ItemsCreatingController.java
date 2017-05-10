package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import woodstore.model.Category;
import woodstore.model.PossibleProduct;
import woodstore.service.impl.CategoryService;
import woodstore.service.impl.PossibleProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by Viktor_Artemov on 5/10/2017.
 */
@Controller
public class ItemsCreatingController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PossibleProductService possibleProductService;


    @RequestMapping(value = "/createNewCategory", method = RequestMethod.POST)
    public String createNewCategory(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String title = request.getParameter("title");
        String type = request.getParameter("categoryType");

        if (title != null && title != "") {
            Category category = null;
            if (categoryService.findByTitle(title) == null) {
                category = new Category();
                category.setCategoryTitle(title);
                if (Integer.valueOf(type) == 0) {
                    category.setSimple(true);
                } else {
                    category.setSimple(false);
                }
                categoryService.add(category);
            }
        } else {
            // TODO: 4/11/2017 send a response about creation failure
        }

        return "redirect:/shipmentin";
    }

    @RequestMapping(value = "/createNewProductFromModal", method = RequestMethod.POST)
    public String createNewProductFromModal(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String title = request.getParameter("title");
        String category = request.getParameter("selectCategory");
        String length = request.getParameter("length");
        String price = request.getParameter("price");
        String width = request.getParameter("width");
        String weight = request.getParameter("weight");

        price = price.replaceAll(",", ".");
        length = length.replaceAll(",", ".");
        width = width.replaceAll(",", ".");
        weight = weight.replaceAll(",", ".");

        // TODO: 4/13/2017 корректно проверять на допустимость введенных данных

        if (title != null && category != null && Double.parseDouble(length) >= 0 && Double.parseDouble(price) > 0) {
            Category productCategory = categoryService.findById(Long.parseLong(category));
            if (productCategory != null) {
                PossibleProduct product = new PossibleProduct();
                product.setTitle(title);
                product.setLength(Double.parseDouble(length));
                product.setPrice(Double.parseDouble(price));
                product.setCategory(productCategory);

                if (width != "") {
                    product.setWidth(Double.parseDouble(width));
                }

                if (weight != "") {
                    product.setWeight(Double.parseDouble(weight));
                }

                possibleProductService.add(product);
            }
        }

        return "redirect:/shipmentin";
    }
}
