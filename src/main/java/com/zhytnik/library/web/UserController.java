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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;

import static com.zhytnik.library.security.UserRole.*;

@Controller
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService service;

    @Autowired
    private MessageSource messageSource;

    public void setUserService(UserService userService) {
        this.service = userService;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @Accessed(ADMIN)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/users/";
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST,
            params = "action=disable")
    public String block(@PathVariable Integer id) {
        service.disable(id);
        return "redirect:/users/";
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST,
            params = "action=activate")
    public String activate(@PathVariable Integer id) {
        service.activate(id);
        return "redirect:/users/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage() {
        return new ModelAndView("register", "user", service.create());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registeёr(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult,
                           @RequestParam(value = "librarian", required = false) boolean librarian,
                           Locale locale) {
        prepare(user, librarian);
        return trySaveAndShowPage(user, bindingResult, locale,
                () -> service.add(user), "register");
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam(value = "librarian", required = false) boolean librarian,
                         Locale locale) {
        prepare(user, librarian);
        return trySaveAndShowPage(user, bindingResult, locale,
                () -> service.update(user), "user/edit");
    }

    private void prepare(User user, boolean librarian) {
        UserRole role = (librarian) ? LIBRARIAN : USER;
        user.setRole(role.toString());
    }

    private String trySaveAndShowPage(User user, BindingResult bindingResult,
                                      Locale locale, Runnable verifyAndSave, String errorPage) {
        if (bindingResult.hasErrors()) {
            return errorPage;
        }
        if (user.getLogin().trim().isEmpty()) {
            FieldError fieldError = new FieldError("user", "login",
                    messageSource.getMessage("user.exception.not.set.login",
                            new String[]{user.getLogin()}, locale));
            bindingResult.addError(fieldError);
            return errorPage;
        }
        try {
            verifyAndSave.run();
            return "redirect:/home/";
        } catch (NotUniqueException e) {
            FieldError fieldError = new FieldError("user", "login",
                    messageSource.getMessage("user.exception.non.unique.login",
                            new String[]{user.getLogin()}, locale));
            bindingResult.addError(fieldError);
            return errorPage;
        }
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users", method = RequestMethod.GET, params = "action=showMe")
    public ModelAndView showUserPage(Principal principal) {
        return new ModelAndView("user/show", "user", service.findByName(principal.getName()));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        User user = service.findById(id);
        user.resetPassword();
        return new ModelAndView("user/edit", "user", user);
    }
}
