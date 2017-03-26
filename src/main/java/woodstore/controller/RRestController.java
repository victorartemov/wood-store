package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.service.impl.CategoryService;
import woodstore.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
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
    public List<Product> admin(HttpServletRequest request) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String title = request.getParameter("title");


        //System.out.println("Что у нас прилетает к ресту? - " + title);

        Category category = categoryService.findByTitle(title);

        return productService.findAll();
    }
}
