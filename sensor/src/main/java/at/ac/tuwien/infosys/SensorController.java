package at.ac.tuwien.infosys;

import at.ac.tuwien.infosys.entities.DataStore;
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

    private static final String INDEX_URL = "/";
    private static final String RECEIVE_URL = "/receive";


    DataStore dataStoreInstance = DataStore.getInstance();

    @RequestMapping(value = RECEIVE_URL, method = RequestMethod.POST)
    public void acceptData(@RequestParam("time") String time, @RequestParam("data") String data) throws Exception {
        dataStoreInstance.putSensorDataFrame(time, data);
    }

    @RequestMapping(value = INDEX_URL, method = RequestMethod.GET)
    public String start(Model model) {
        return "index";
    }


    @RequestMapping(value = RECEIVE_URL, method = RequestMethod.GET)
    public String getTimeStamps(Model model) {
        List<String> timeStamps = dataStoreInstance.getTimeStampsList();
        List<String> timeStampsFormatted =  new ArrayList<>();
        for (String timeStamp:timeStamps){
            long time= Long.parseLong(timeStamp);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
            timeStampsFormatted.add(sdf.format(time));
        }
        model.addAttribute(ModelAttributes.OBJECT_LIST, timeStampsFormatted);
        return "receive";
    }



}
