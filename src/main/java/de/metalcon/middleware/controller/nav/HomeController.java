package de.metalcon.middleware.controller.nav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import de.metalcon.middleware.controller.BaseController;
import de.metalcon.middleware.test.TestData;

@Controller
public class HomeController extends BaseController {

    @Autowired
    TestData testData;

    public ModelAndView mappingHome() {
        ModelAndView mv = new ModelAndView("nav/home");
        mv.addObject("jamesHetfieldMuid", testData.jamesHetfieldMuid.toString());
        mv.addObject("ensiferumMuid", testData.ensiferumMuid.toString());
        mv.addObject("ensiferum2Muid", testData.ensiferum2Muid.toString());
        mv.addObject("victorySongsMuid", testData.victorySongsMuid.toString());
        mv.addObject("ahtiMuid", testData.ahtiMuid.toString());
        mv.addObject("druckkammerMuid", testData.druckkammerMuid.toString());
        mv.addObject("wackenMuid", testData.wackenMuid.toString());
        mv.addObject("koblenzMuid", testData.koblenzMuid.toString());
        mv.addObject("blackMetalMuid", testData.blackMetalMuid.toString());
        mv.addObject("guitarMuid", testData.guitarMuid.toString());
        mv.addObject("heidenfestMuid", testData.heidenfestMuid.toString());
        return mv;
    }

}
