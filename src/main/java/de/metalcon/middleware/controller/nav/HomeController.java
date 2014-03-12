package de.metalcon.middleware.controller.nav;

import org.springframework.stereotype.Controller;

import de.metalcon.middleware.controller.MetalconController;

@Controller
public class HomeController extends MetalconController {

    public String handleHome() {
        return "nav/home";
    }

}
