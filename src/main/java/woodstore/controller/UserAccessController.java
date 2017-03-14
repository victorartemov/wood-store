package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import woodstore.model.Category;
import woodstore.model.Product;
import woodstore.model.Profile;
import woodstore.service.SecurityService;
import woodstore.service.impl.CategoryService;
import woodstore.service.impl.ProductService;
import woodstore.service.impl.ProfileService;
import woodstore.validator.ProfileValidator;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Viktor_Artemov on 3/2/2017.
 */
@Controller
public class UserAccessController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ProfileValidator profileValidator;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new Profile());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Profile userForm, BindingResult bindingResult, Model model) {
        profileValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        profileService.add(userForm);

        securityService.autoLogin(userForm.getName(), userForm.getConfirmPassword());

        return "redirect:/welcome";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {

        String currentTime = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        model.addAttribute("currentTime", currentTime);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        Map<String, List<Product>> productsByCategories = new HashMap<>();

        for (Category category : categories){
            productsByCategories.put(category.getTitle(), productService.findByCategory(category));
        }

        model.addAttribute("productsByCategories", productsByCategories);

        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/workday", method = RequestMethod.GET)
    public String workday(Model model){
        return "workday";
    }
}
