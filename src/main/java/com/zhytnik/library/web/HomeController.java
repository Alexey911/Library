package com.zhytnik.library.web;

import com.zhytnik.library.security.MinAccessed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static com.zhytnik.library.security.UserRole.USER;

@Controller
public class HomeController {
    @MinAccessed(USER)
    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public ModelAndView showHomePage() {
        return new ModelAndView("index", "info", "Hello User :)");
    }
}
