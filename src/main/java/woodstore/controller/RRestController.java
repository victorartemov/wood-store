package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.service.impl.CategoryService;
import woodstore.service.impl.ProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor_Artemov on 3/23/2017.
 */

@RestController()
public class RRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
    public List<Product> admin(@RequestParam(value = "title") String title) {
        Category category = categoryService.findByTitle(title);

        return productService.findAll();
    }
}
