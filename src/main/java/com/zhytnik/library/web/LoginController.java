package com.zhytnik.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView showAdminPage(ModelAndView model) {
        model.addObject("title", "Spring Security Custom Login Form");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, params = "error")
    public ModelAndView loginError() {
        return new ModelAndView("login", "error", "Invalid username and password!");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, params = "msg")
    public ModelAndView logout() {
        return new ModelAndView("login", "msg", "You've been logged out successfully.");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}