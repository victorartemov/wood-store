package woodstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import woodstore.model.*;
import woodstore.service.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
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
    private StoreService storeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PossibleProductService possibleProductService;

    @Autowired
    private ShipmentInService shipmentInService;

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
}
