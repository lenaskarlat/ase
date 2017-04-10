package at.ac.tuwien.infosys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lenaskarlat on 4/10/17.
 */
@Controller
public class SensorController {

    private static final String RECEIVE = "/receive";
//
//    @Autowired
//    public SensorController() {
//
//    }
    @RequestMapping(value=RECEIVE, method = RequestMethod.GET)
    public String receiveData(Model model){
        return "receive";
    }


}
