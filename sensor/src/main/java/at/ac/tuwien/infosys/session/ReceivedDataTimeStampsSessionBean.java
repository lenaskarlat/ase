package at.ac.tuwien.infosys.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by lenaskarlat on 4/18/17.
 */
@Component
@Scope(value = "session")
public class ReceivedDataTimeStampsSessionBean {
    private List<String> timeStampsWorkingList;

    public void init(List<String> timeStamps, Model model) {
        timeStampsWorkingList = timeStamps;
        saveTo(model);
    }

    public void initContinue(Model model) { saveTo(model); }

    private void saveTo(Model model) {
        model.addAttribute("objectList", timeStampsWorkingList);
    }

    public List<String> getTimeStampsWorkingList() {
        return timeStampsWorkingList;
    }

}
