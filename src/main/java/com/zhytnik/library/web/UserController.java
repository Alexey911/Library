package com.zhytnik.library.web;

import com.zhytnik.library.domain.User;
import com.zhytnik.library.security.Accessed;
import com.zhytnik.library.security.MinAccessed;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

import static com.zhytnik.library.security.UserRole.*;

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

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", service.create());
        return "register";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Valid User user,
                           @RequestParam(value = "librarian", required = false) boolean librarian,
                           BindingResult bindingResult, Locale locale) {
        UserRole role = (librarian) ? LIBRARIAN : USER;
        user.setRole(role.getAuthority());
        if (bindingResult.hasErrors() || trySaveAndCheckErrors(user, bindingResult,
                locale, () -> service.add(user))) {
            return "register";
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
