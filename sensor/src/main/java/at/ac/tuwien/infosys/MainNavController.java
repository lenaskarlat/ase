package at.ac.tuwien.infosys;

import at.ac.tuwien.infosys.ui.MessagesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by lenaskarlat on 4/10/17.
 */
@Controller
public class MainNavController {
    public static final String INDEX_URL="/";

    @RequestMapping(INDEX_URL)
    public String index() {return "index";}

    public static String redirectWithError(RedirectAttributes redirectAttributes, String url, String message, Throwable throwable) {
        MessagesUtil.add(redirectAttributes, message, throwable);
        return "redirect:" + url;
    }
}
