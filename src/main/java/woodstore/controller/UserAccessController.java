package woodstore.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
import java.time.temporal.ChronoUnit;
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

    @Autowired
    private ShipmentOutService shipmentOutService;

    @Autowired
    private SentProductService sentProductService;

    @Autowired
    private PossibleProductService possibleProductService;

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
                    totalSum += product.getPrice() * 0.096 * product.getLength() * product.getAmount();
                }
            }
            DecimalFormat df = new DecimalFormat("#.0");
            model.addAttribute("totalSum", df.format(totalSum));
        } else {
            model.addAttribute("dayIsOpen", false);
        }

        return "workday";
    }

    @RequestMapping(value = "/createNewDay", method = RequestMethod.GET)
    public String createNewDay(Model model) {

        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

        Workday newWorkday = new Workday();
        newWorkday.setDate(dateFormatter.format(currentDate));
        newWorkday.setOpen(true);

        workdayService.add(newWorkday);

        return "redirect:/workday";
    }

    @RequestMapping(value = "/openTheDay", method = RequestMethod.GET)
    public String openTheDay(Model model) {
        Workday currentWorkday = workdayService.today();
        if (currentWorkday != null) {
            currentWorkday.setOpen(true);
            workdayService.edit(currentWorkday);
        }

        return "redirect:/workday";
    }

    @RequestMapping(value = "/closeTheDay", method = RequestMethod.GET)
    public String closeTheDay(Model model) {
        Workday currentWorkday = workdayService.today();
        if (currentWorkday != null) {
            currentWorkday.setOpen(false);
            workdayService.edit(currentWorkday);
        }

        return "redirect:/workday";
    }

    @RequestMapping(value = "/shipmentin", method = RequestMethod.GET)
    public String shipmentin(Model model) {

        ShipmentIn currentShipment = shipmentInService.getCurrentShipment();
        model.addAttribute("currentShipment", currentShipment);

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

        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        ShipmentIn currentShipment = new ShipmentIn();
        currentShipment.setDate(dateFormatter.format(currentDate));

        shipmentInService.add(currentShipment);

        return "redirect:/shipmentin";
    }

    @RequestMapping(value = "/closeCurrentShipmentIn", method = RequestMethod.GET)
    public String closeCurrentShipmentIn(Model model) {

        //пополняем склад всеми товарами из прихода
        ShipmentIn currentShipment = shipmentInService.getCurrentShipment();

        Collection<RecievedProduct> recievedProducts = currentShipment.getProducts();
        for (RecievedProduct product : recievedProducts) {

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

            BasicProduct storedProduct = null;

            if (productService.findByTitle(title) == null) {
                storedProduct = possibleProductService.findByTitle(title);
            } else {
                storedProduct = productService.findByTitle(title);
            }

            RecievedProduct recievedProduct = new RecievedProduct(storedProduct);

            if (Integer.parseInt(quantity) == 0) {
                redirectAttributes.addFlashAttribute("formInputError", "Введите количество товара");
                return "redirect:/shipmentin";
            }

            if (Integer.parseInt(quantity) > 0 && quantity != "") {
                recievedProduct.setAmount(Integer.parseInt(quantity));
                recievedProduct.setCategory(storedProduct.getCategory());

                recievedProductService.add(recievedProduct);

                ShipmentIn currentShipment = shipmentInService.getCurrentShipment();

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

            if (storedProduct == null) {
                redirectAttributes.addFlashAttribute("formInputError", "На складе больше нет данного товара");
                return "redirect:/workday";
            }
            SoldProduct soldProduct = new SoldProduct(storedProduct);

            if (Integer.parseInt(quantity) == 0) {
                redirectAttributes.addFlashAttribute("formInputError", "Введите количество товара");
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

    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public String journal(Model model) {

        return "journal";
    }

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

    @RequestMapping(value = "/shipmentInList", method = RequestMethod.GET)
    public String shipmentInList(Model model) {

        return "shipmentInList";
    }

    @RequestMapping(value = "/shipmentOutList", method = RequestMethod.GET)
    public String shipmentOutList(Model model) {

        return "shipmentOutList";
    }

    @RequestMapping(value = "/workdayList", method = RequestMethod.GET)
    public String workdayList(Model model) {

        return "workdayList";
    }

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

        price = price.replaceAll(",", ".");
        length = length.replaceAll(",", ".");

        if (title != null && category != null && Double.parseDouble(length) > 0 && Double.parseDouble(price) > 0) {
            Category productCategory = categoryService.findById(Long.parseLong(category));
            if(productCategory!=null){
                PossibleProduct product = new PossibleProduct();
                product.setTitle(title);
                product.setLength(Double.parseDouble(length));
                product.setPrice(Double.parseDouble(price));
                product.setCategory(productCategory);

                possibleProductService.add(product);
            }
        }

        return "redirect:/shipmentin";
    }
}
