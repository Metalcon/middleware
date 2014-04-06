package de.metalcon.middleware.controller.test;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestBandController {

    @Autowired
    private BeanFactory beanFactory;

    public ModelAndView handleRequest() {

        List<String> jsonAnswers = new LinkedList<String>();

        Object answer;
        jsonAnswers.add("test");
        jsonAnswers.add("test1");

        ModelMap model = new ModelMap();
        model.addAttribute("jsonAnswers", jsonAnswers);
        return new ModelAndView("test/model", model);
    }

    public ModelAndView handleRequestByBandName(
            @PathVariable("bandName") String bandName) {
        ModelMap model = new ModelMap();
        return new ModelAndView("test/band", model);
    }

}
