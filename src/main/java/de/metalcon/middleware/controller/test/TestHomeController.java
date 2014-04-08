package de.metalcon.middleware.controller.test;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import de.metalcon.middleware.core.UserSession;

@Controller
public class TestHomeController {

    //private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserSession.Factory userSessionFactory;

    public ModelAndView home() {
        List<String> bands = new LinkedList<String>();
        bands.add("Ensiferum");
        bands.add("Manowar");
        bands.add("Blind Guardian");
        bands.add("Bolt Thrower");
        bands.add("another test");

        UserSession user = userSessionFactory.userSession();
        bands.add("user id: " + user.getId());
        user.incPageCount();
        bands.add("page count: " + user.getPageCount());

        ModelMap model = new ModelMap();
        model.addAttribute("bands", bands);
        return new ModelAndView("test/home", model);
    }

    public ModelAndView handleFtlError() {
        return new ModelAndView("test/ftlerror");
    }

}
