package de.metalcon.middleware.controller.nav;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import de.metalcon.middleware.controller.BaseController;

@Controller
public class HomeController extends BaseController {

    public ModelAndView mappingHome() {
        ModelAndView mv = new ModelAndView("nav/home");
        return mv;
    }

}
