package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import woodstore.model.*;
import woodstore.service.impl.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor_Artemov on 3/23/2017.
 */

@RestController()
public class AppRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PossibleProductService possibleProductService;

    @Autowired
    private ShipmentInService shipmentInService;

    @Autowired
    private ShipmentOutService shipmentOutService;

    @Autowired
    private WorkdayService workdayService;

    @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
    public List<PossibleProduct> getProducts(@RequestParam(value = "id", required = true) Long id) {
        return possibleProductService.findMatchesById(Long.valueOf(id));
    }

    @RequestMapping(value = "/getShipments", method = RequestMethod.GET)
    public List<ShipmentIn> getShipments(@RequestParam(value = "date", required = true) String date) {

        List<ShipmentIn> shipmentIns = shipmentInService.findAll();
        List<ShipmentIn> resultList = new ArrayList<>();
        for (ShipmentIn shipment : shipmentIns) {
            if (shipment.getDate().equals(date)) {
                resultList.add(shipment);
            }
        }

        return resultList;
    }

    @RequestMapping(value = "/getShipmentOuts", method = RequestMethod.GET)
    public List<ShipmentOut> getShipmentOuts(@RequestParam(value = "date", required = true) String date) {

        List<ShipmentOut> shipmentOuts = shipmentOutService.findAll();
        List<ShipmentOut> resultList = new ArrayList<>();
        for (ShipmentOut shipment : shipmentOuts) {
            if (shipment.getDate().equals(date)) {
                resultList.add(shipment);
            }
        }

        return resultList;
    }

    @RequestMapping(value = "/getWorkday", method = RequestMethod.GET)
    public Workday getWorkday(@RequestParam(value = "date", required = true) String date) {

        String dateAppropriateForDb = date.replaceAll("/", ".");
        Workday workday = workdayService.findByDate(dateAppropriateForDb);

        return workday;
    }

    @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryService.findAll();
    }
}
