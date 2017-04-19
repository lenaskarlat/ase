package at.ac.tuwien.infosys;

import at.ac.tuwien.infosys.entities.DataStore;
import at.ac.tuwien.infosys.entities.SensorDataFrame;
import at.ac.tuwien.infosys.ui.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenaskarlat on 4/10/17.
 */
@Controller
public class MainNavController {
    public static final String INDEX_URL="/";
    public static final String RECEIVE_URL="/receive";


    DataStore dataStoreInstance = DataStore.getInstance();

    @RequestMapping(INDEX_URL)
    public String index() {return "index";}


    @RequestMapping(RECEIVE_URL)
    public String receive(Model model, RedirectAttributes redirectAttributes) {
        try {
            List<String> timeStamps = dataStoreInstance.getTimeStampsList();
            List<String> timeStampsFormatted =  new ArrayList<>();
            for (String timeStamp:timeStamps){
                long time= Long.parseLong(timeStamp);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
                timeStampsFormatted.add(sdf.format(time));
            }
            model.addAttribute(ModelAttributes.OBJECT_LIST, timeStampsFormatted);

        } catch (Exception ex) {
            return redirectWithError(redirectAttributes, MainNavController.INDEX_URL, "Unable to get access to the data", ex);
        }
        return "receive";
    }


    /**
     * Utility function, redirecting to a page with an error message.
     *
     * @param redirectAttributes the attribute object for writing the message to
     * @param url                the URL to redirect to
     * @param message            the message to show
     * @param throwable          the throwable causing the message
     */
    public static String redirectWithError(RedirectAttributes redirectAttributes, String url, String message, Throwable throwable) {
        MessagesUtil.add(redirectAttributes, message, throwable);
        return "redirect:" + url;
    }
}
