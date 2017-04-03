package com.futoria.system.controller;

import com.futoria.system.service.TestService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/me/tests")
public class TestListController {
    private TestService testService;
    private Gson gson;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getMyTests() {
        ModelAndView modelAndView = new ModelAndView("test_list.jsp");

        modelAndView.addObject("tests", gson.toJson(testService.getMyTests()));

        return modelAndView;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setGson(@Qualifier("FutoriaGson") Gson gson) {
        this.gson = gson;
    }
}
