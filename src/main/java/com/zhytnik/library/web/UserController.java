package com.zhytnik.library.web;

import com.zhytnik.library.security.Accessed;
import com.zhytnik.library.security.MinAccessed;
import com.zhytnik.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static com.zhytnik.library.security.UserRole.ADMIN;
import static com.zhytnik.library.security.UserRole.USER;

@Controller
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService service;

    @Autowired
    private MessageSource messageSource;

    @Accessed(ADMIN)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("user/showAll", "users", service.getAll());
    }


    @MinAccessed(USER)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("user/show", "user", service.findById(id));
    }

    @MinAccessed(ADMIN)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/users/";
    }
}
