package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import woodstore.model.*;
import woodstore.service.SecurityService;
import woodstore.service.impl.*;
import woodstore.validator.ProfileValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DecimalFormat;
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

    @Autowired
    private SoldProductService soldProductService;

    @Autowired
    private ShipmentInService shipmentInService;

    @Autowired
    private RecievedProductService recievedProductService;

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

        for (Category category : categories) {
            productsByCategories.put(category.getTitle(), productService.findByCategory(category));
        }

        model.addAttribute("productsByCategories", productsByCategories);

        double totalSum = 0;
        for (Product product : productService.findAll()) {
            if (product.getCategory().isSimple()) {
                totalSum += product.getPrice() * product.getAmount();
            } else {
                totalSum += product.getPrice() * product.getAmount() * product.getLength() * 0.096;
            }
        }
        DecimalFormat df = new DecimalFormat("#.0");
        model.addAttribute("totalSum", df.format(totalSum));

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

        Workday currentWorkDay = workdayService.findByDate(dateFormatter.format(currentDate));
        model.addAttribute("currentWorkDay", currentWorkDay);

        List<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("workwithproduct.jsp");
        modelAndView.addObject("categories", categories);
        model.addAttribute("categories", categories);

        if (workdayService.today() != null) {
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
                    totalSum += product.getPrice() * 0.096 * product.getLength() * product.getAmount();
                }
            }
            DecimalFormat df = new DecimalFormat("#.0");
            model.addAttribute("totalSum", df.format(totalSum));
        }

        return "workday";
    }

    @RequestMapping(value = "/shipmentout", method = RequestMethod.GET)
    public String shipmentout(Model model) {
        return "shipmentout";
    }

    @RequestMapping(value = "/shipmentin", method = RequestMethod.GET)
    public String shipmentin(Model model) {

        ShipmentIn currentShipment = null;

        List<ShipmentIn> shipmentIns = shipmentInService.findAll();
        for (ShipmentIn shipmentIn : shipmentIns) {
            if (!shipmentIn.isClosed()) {
                currentShipment = shipmentIn;
                model.addAttribute("currentShipment", shipmentIn);
            }
        }

        if (currentShipment != null) {

            List<Category> categories = new ArrayList<>();
            for (RecievedProduct product : currentShipment.getProducts()) {
                if (!categories.contains(product.getCategory())) {
                    categories.add(product.getCategory());
                }
            }
            model.addAttribute("categories", categories);


            List<Category> allCategories = categoryService.findAll();
            model.addAttribute("allCategories", allCategories);

            Map<String, List<RecievedProduct>> productsByCategories = new HashMap<>();
            for (Category category : categories) {
                ArrayList<RecievedProduct> products = new ArrayList<>();
                for (RecievedProduct product : currentShipment.getProducts()) {
                    if (product.getCategory().getTitle() == category.getTitle()) {
                        products.add(product);
                    }
                }
                productsByCategories.put(category.getTitle(), products);
            }
            model.addAttribute("productsByCategories", productsByCategories);

            double totalSum = 0;
            for (RecievedProduct product : currentShipment.getProducts()) {
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

        return "shipmentin";
    }

    @RequestMapping(value = "/createnewshipmentin", method = RequestMethod.GET)
    public String createnewshipmentin(Model model) {

        ShipmentIn currentShipment = new ShipmentIn();
        shipmentInService.add(currentShipment);

        return "redirect:/shipmentin";
    }

    @RequestMapping(value = "/closeCurrentShipmentIn", method = RequestMethod.GET)
    public String closeCurrentShipmentIn(Model model){

        //пополняем склад всеми товарами из прихода
        ShipmentIn currentShipment = shipmentInService.getCurrentShipment();

        Collection<RecievedProduct> recievedProducts = currentShipment.getProducts();
        for(RecievedProduct product : recievedProducts){
            Product storedProduct = productService.findByTitle(product.getTitle());
            storedProduct.setAmount(storedProduct.getAmount() + product.getAmount());
            productService.edit(storedProduct);
        }

        //закрываем приход
        List<ShipmentIn> shipmentIns = shipmentInService.findAll();
        for(ShipmentIn shipment: shipmentIns){
            if(!shipment.isClosed()){
                shipment.close();
                shipmentInService.edit(shipment);
            }
        }

        return "redirect:/shipmentin";
    }

    @RequestMapping(value = "/createNewShipmentInProduct", method = RequestMethod.POST)
    public String createNewShipmentInProduct(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String title = request.getParameter("selectProduct");
        String quantity = request.getParameter("quantity");

        if (title != null && title != "") {
            Product storedProduct = productService.findByTitle(title);
            RecievedProduct recievedProduct = new RecievedProduct(storedProduct);

            if (Integer.parseInt(quantity) > 0) {
                recievedProduct.setAmount(Integer.parseInt(quantity));
                recievedProduct.setCategory(storedProduct.getCategory());

                recievedProductService.add(recievedProduct);

                ShipmentIn currentShipment = null;

                List<ShipmentIn> shipmentIns = shipmentInService.findAll();
                for (ShipmentIn shipmentIn : shipmentIns) {
                    if (!shipmentIn.isClosed()) {
                        currentShipment = shipmentIn;
                    }
                }

                currentShipment.getProducts().add(recievedProduct);
                shipmentInService.edit(currentShipment);

                redirectAttributes.addFlashAttribute("formInputError", null);
            } else {
                redirectAttributes.addFlashAttribute("formInputError", "Недопустимое количество товара");
            }
        } else {
            redirectAttributes.addFlashAttribute("formInputError", "Не выбран товар для прихода");
        }

        return "redirect:/shipmentin";
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
    public String createnewproduct(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String title = request.getParameter("selectProduct");
        String quantity = request.getParameter("quantity");

        if (title != null && title != "") {
            Product storedProduct = productService.findByTitle(title);
            SoldProduct soldProduct = new SoldProduct(storedProduct);

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
                redirectAttributes.addFlashAttribute("formInputError", "Недопустимое количество товара для продажи");
            }
        } else {
            redirectAttributes.addFlashAttribute("formInputError", "Не выбран товар для продажи");
        }

        return "redirect:/workday";
    }

    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public String journal(Model model) {

        return "journal";
    }
}
