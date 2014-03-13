package de.metalcon.middleware.controller.test;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import de.metalcon.middleware.core.request.legacy.JsonRequest;
import de.metalcon.middleware.core.request.legacy.RequestTransaction;

@Controller
public class TestBandController {

    @Autowired
    private BeanFactory beanFactory;

    public ModelAndView handleRequest() {
        RequestTransaction tx = beanFactory.getBean(RequestTransaction.class);
        tx.request(new JsonRequest("http://headers.jsontest.com/"));
        tx.request(new JsonRequest("http://ip.jsontest.com/"));
        tx.request(new JsonRequest("http://date.jsontest.com/"));
        tx.request(new JsonRequest("http://echo.jsontest.com/metalcon/rocks"));
        tx.request(new JsonRequest("http://md5.jsontest.com/?text=metalcon"));

        List<String> jsonAnswers = new LinkedList<String>();

        Object answer;
        while ((answer = tx.recieve()) != null) {
            jsonAnswers.add((String) answer);
        }

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
