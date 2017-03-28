package com.futoria.system.controller;

import com.futoria.core.application.configuration.security.FutoriaSecurityService;
import com.futoria.core.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/me")
public class MyPageController {
    private FutoriaSecurityService securityService;
    private UserService userService;
    private Gson gson;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView myPage() {
        ModelAndView modelAndView = new ModelAndView("my_page.jsp");

        modelAndView.addObject("user", gson.toJson(securityService.getUserFromContext()));
        modelAndView.addObject("userHeadMaster", gson.toJson(userService.getMyHeadMaster()));

        return modelAndView;
    }

    @Autowired
    public void setSecurityService(@Qualifier("CoreSecurityService")
                                           FutoriaSecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setUserService(@Qualifier("CoreUserService") UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setGson(@Qualifier("FutoriaGson") Gson gson) {
        this.gson = gson;
    }
}
