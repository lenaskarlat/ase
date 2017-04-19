package at.ac.tuwien.infosys;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenaskarlat on 4/10/17.
 */
@Controller
public class SensorController {

    private static final String START = "/";
    private static final String RECEIVE = "/receive";

    private List<String> timeStamps=new ArrayList<>();

    @RequestMapping(value = RECEIVE, method = RequestMethod.POST)
    public void acceptData(@RequestParam("time") String time) throws Exception {
        long timeStamp= Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
        timeStamps.add(sdf.format(timeStamp));
    }

    @RequestMapping(value = START, method = RequestMethod.GET)
    public String start(Model model) {
        return "index";
    }


    @RequestMapping(value = RECEIVE, method = RequestMethod.GET)
    public String getData(Model model) {
        model.addAttribute("objectList", timeStamps);
        return "receive";
    }



}
