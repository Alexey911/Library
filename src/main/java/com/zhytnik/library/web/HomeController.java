package com.zhytnik.library.web;

import com.zhytnik.library.security.MinAccessed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

import static com.zhytnik.library.security.UserRole.USER;

@Controller
public class HomeController {
    @Autowired
    private MessageSource messageSource;

    @MinAccessed(USER)
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView showHomePage(Locale locale) {
        return getGeneralModelAndView(locale);
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView showWelcomePage(Locale locale) {
        return getGeneralModelAndView(locale);
    }

    private ModelAndView getGeneralModelAndView(Locale locale) {
        String message = messageSource.getMessage("enter.page.title", new String[]{}, locale);
        return new ModelAndView("index", "info", message);
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
