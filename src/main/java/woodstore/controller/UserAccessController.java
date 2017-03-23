package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import woodstore.model.*;
import woodstore.service.SecurityService;
import woodstore.service.impl.*;
import woodstore.validator.ProfileValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;


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

    @Autowired
    private StoreService storeService;

    @Autowired
    private WorkdayService workdayService;

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
    public String workday(Model model) {

        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        //model.addAttribute("currentTime", dateFormatter.format(currentDate));

        Workday currentWorkDay = workdayService.findByDate(dateFormatter.format(currentDate));
        model.addAttribute("currentWorkDay", currentWorkDay);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "workday";
    }

    @RequestMapping(value = "/shipmentout", method = RequestMethod.GET)
    public String shipmentout(Model model) {
        return "shipmentout";
    }

    @RequestMapping(value = "/shipmentin", method = RequestMethod.GET)
    public String shipmentin(Model model) {
        return "shipmentin";
    }

    @RequestMapping(value = "/createnewday", method = RequestMethod.GET)
    public String createnewday(Model model) {

        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

        Workday newWorkday = new Workday();
        newWorkday.setDate(dateFormatter.format(currentDate));

        workdayService.add(newWorkday);

        return "redirect:/workday";
    }

    @RequestMapping(value = "/createnewproduct", method = RequestMethod.POST)
    public String createnewdayA(HttpServletRequest request) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Enumeration en = request.getParameterNames();
        while(en.hasMoreElements()) {
            // Get the name of the request parameter
            String name = (String)en.nextElement();
            System.out.println(name);

            // Get the value of the request parameter
            String value = request.getParameter(name);

            // If the request parameter can appear more than once in the query string, get all values
            String[] values = request.getParameterValues(name);

            for (int i=0; i<values.length; i++) {
                System.out.println(" " + values[i]);
            }
        }
        return "redirect:/workday";
    }
}
