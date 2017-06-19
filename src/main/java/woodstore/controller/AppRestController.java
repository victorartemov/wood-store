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

    @RequestMapping(value = "/get-products", method = RequestMethod.GET)
    public List<PossibleProduct> getProducts(@RequestParam(value = "id", required = true) Long id) {
        return possibleProductService.findMatchesById(Long.valueOf(id));
    }

    @RequestMapping(value = "/get-shipments", method = RequestMethod.GET)
    public List<ShipmentIn> getShipments(@RequestParam(value = "date", required = true) String date) {

        List<ShipmentIn> shipmentIns = shipmentInService.findAll();
        for(ShipmentIn shipmentIn:shipmentIns){
            System.out.println(shipmentIn.getId() + shipmentIn.getDate());
        }
        List<ShipmentIn> resultList = new ArrayList<>();
        for (ShipmentIn shipment : shipmentIns) {
            if (shipment.getDate().equals(date)) {
                resultList.add(shipment);
            }
        }

        return resultList;
    }

    @RequestMapping(value = "/get-shipment-outs", method = RequestMethod.GET)
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

    @RequestMapping(value = "/get-workday", method = RequestMethod.GET)
    public Workday getWorkday(@RequestParam(value = "date", required = true) String date) {

        String dateAppropriateForDb = date.replaceAll("/", ".");
        Workday workday = workdayService.findByDate(dateAppropriateForDb);

        return workday;
    }

    @RequestMapping(value = "/get-categories", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryService.findAll();
    }
}
