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
}
