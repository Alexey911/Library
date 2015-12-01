package com.zhytnik.library.web;

import com.zhytnik.library.domain.User;
import com.zhytnik.library.security.UserRole;
import com.zhytnik.library.service.UserService;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

import static com.zhytnik.library.security.UserRole.LIBRARIAN;
import static com.zhytnik.library.security.UserRole.USER;

@Controller
public class LoginController {
    @Autowired
    @Qualifier("userService")
    private UserService service;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView showAdminPage(ModelAndView model) {
        model.addObject("title", "Spring SecurityMetadataAnalyzer Custom Login Form");
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

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", service.create());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Valid User user,
                           @RequestParam("librarian") boolean isLibrarian,
                           BindingResult bindingResult, Locale locale) {
        UserRole role = (isLibrarian) ? LIBRARIAN : USER;
        user.setRole(role);
        if (bindingResult.hasErrors() || trySaveAndCheckErrors(user, bindingResult,
                locale, () -> service.add(user))) {
            return "registration";
        }
        return "redirect:home";
    }

    private boolean trySaveAndCheckErrors(User user, BindingResult bindingResult,
                                          Locale locale, Runnable verifyAndSave) {
        if (user.getLogin().trim().isEmpty()) {
            FieldError fieldError = new FieldError("user", "login",
                    messageSource.getMessage("user.exception.not.set.login",
                            new String[]{user.getLogin()}, locale));
            bindingResult.addError(fieldError);
            return true;
        }
        boolean isUnique = true;
        try {
            verifyAndSave.run();
        } catch (NotUniqueException e) {
            isUnique = false;
            FieldError fieldError = new FieldError("user", "login",
                    messageSource.getMessage("user.exception.non.unique.login",
                            new String[]{user.getLogin()}, locale));
            bindingResult.addError(fieldError);
        }
        return !isUnique;
    }
}