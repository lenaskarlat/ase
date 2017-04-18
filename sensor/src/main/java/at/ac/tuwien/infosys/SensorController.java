package at.ac.tuwien.infosys;


import at.ac.tuwien.infosys.entities.SensorDataFrame;
import at.ac.tuwien.infosys.session.SessionProxy;
import at.ac.tuwien.infosys.ui.forms.ReceivedDataTimeStampsForm;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenaskarlat on 4/10/17.
 */
@Controller
public class SensorController {

    private static final String START = "/";
    private static final String RECEIVE = "/receive";

    private final SessionProxy sessionProxy;

   // private List<SensorDataFrame> sdfList=new ArrayList<>();

    @Autowired
    public SensorController (SessionProxy sessionProxy){
        this.sessionProxy=sessionProxy;
    }

    protected void initReceivedDataTimeStamps (Model model) {sessionProxy.getReceivedDataTimeStampsSessionBean().init(new ArrayList<>(),model);}


    @RequestMapping(value = RECEIVE, method = RequestMethod.POST)
    public void acceptData(@RequestParam("time") String time) throws Exception {
       sessionProxy.getReceivedDataTimeStampsSessionBean().getTimeStampsWorkingList().add(time);


    }

    @RequestMapping(value = START, method = RequestMethod.GET)
    public String start(Model model) {
        initReceivedDataTimeStamps(model);
        return "index";
    }


    @RequestMapping(value = RECEIVE, method = RequestMethod.GET)
    public String getData(Model model) {

        return "receive";
    }



}
