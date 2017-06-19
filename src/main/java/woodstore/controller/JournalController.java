package woodstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Viktor_Artemov on 5/10/2017.
 */
@Controller
public class JournalController {

    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public String journal(Model model) {
        return "journal";
    }

    @RequestMapping(value = "/shipment-in-list", method = RequestMethod.GET)
    public String shipmentInList(Model model) {
        return "shipment-in-list";
    }

    @RequestMapping(value = "/shipment-out-list", method = RequestMethod.GET)
    public String shipmentOutList(Model model) {
        return "shipment-out-list";
    }

    @RequestMapping(value = "/workday-list", method = RequestMethod.GET)
    public String workdayList(Model model) {
        return "workday-list";
    }
}
