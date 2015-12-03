package com.zhytnik.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class LoginController {
    @Autowired
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, params = "error")
    public ModelAndView loginError(Locale locale) {
        return new ModelAndView("login", "error",
                getString("invalid.username.or.password", locale));
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, params = "msg")
    public ModelAndView logout(Locale locale) {
        return new ModelAndView("login", "msg", getString("logged.out", locale));
    }

    private String getString(String name, Locale locale) {
        return messageSource.getMessage(name, new Object[]{}, locale);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
