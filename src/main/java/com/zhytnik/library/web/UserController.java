package com.zhytnik.library.web;

import com.zhytnik.library.domain.User;
import com.zhytnik.library.security.Accessed;
import com.zhytnik.library.security.MinAccessed;
import com.zhytnik.library.security.UserRole;
import com.zhytnik.library.service.UserService;
import com.zhytnik.library.service.exception.NotUniqueException;
import com.zhytnik.library.service.exception.PasswordMismatchException;
import com.zhytnik.library.tools.PasswordWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
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
    @RequestMapping(value = "/users", method = RequestMethod.GET,
            params = "action=confirm")
    public ModelAndView showNotConfirmedUsers() {
        return new ModelAndView("user/confirm", "users", service.getNotConfirmedUsers());
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users/confirm", method = RequestMethod.POST)
    public String confirm(@RequestParam(value = "users", required = false) List<Integer> users) {
        service.confirm(users);
        return "redirect:/users/";
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/login";
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
    public String register(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult,
                           @RequestParam(value = "librarian", required = false) boolean librarian,
                           Locale locale) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (!isLoginFilled(user, bindingResult, locale)) {
            return "register";
        }

        UserRole role = (librarian) ? LIBRARIAN : USER;
        user.setRole(role.toString());

        if (!trySaveUser(user, bindingResult, () -> service.add(user), locale)) {
            return "register";
        }
        return "redirect:/home";
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/updatePassword", method = RequestMethod.POST)
    public String updatePassword(@ModelAttribute("wrapper") @Valid PasswordWrapper wrapper,
                                 BindingResult bindingResult,
                                 Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "user/changePassword";
        }
        try {
            service.updatePassword(wrapper.getOwnerId(),
                    wrapper.getLastPassword(), wrapper.getNewPassword());
        } catch (PasswordMismatchException e) {
            FieldError fieldError = new FieldError("wrapper", "lastPassword",
                    messageSource.getMessage("password.exception.password.mismatch",
                            new String[]{}, locale));
            bindingResult.addError(fieldError);
            return "user/changePassword";
        }
        logout(request);
        return "redirect:/login?logout";
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public String updateInfo(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             @RequestParam(value = "librarian", required = false) boolean librarian,
                             Locale locale, HttpServletRequest request) {
        //checking password, it's missing
        if (bindingResult.getErrorCount() > 1) {
            return "user/edit";
        }
        if (!isLoginFilled(user, bindingResult, locale)) {
            return "user/edit";
        }
        if (!trySaveUser(user, bindingResult, () -> service.updateLoginRole(user.getId(),
                user.getLogin(), librarian), locale)) {
            return "user/edit";
        }
        logout(request);
        return "redirect:/login?logout";
    }

    private void logout(HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, null, null);
    }

    private boolean isLoginFilled(User user, BindingResult bindingResult, Locale locale) {
        boolean filled = true;
        if (user.getLogin().trim().isEmpty()) {
            FieldError fieldError = new FieldError("user", "login",
                    messageSource.getMessage("user.exception.not.set.login",
                            new String[]{user.getLogin()}, locale));
            bindingResult.addError(fieldError);
            filled = false;
        }
        return filled;
    }

    private boolean trySaveUser(User user, BindingResult bindingResult,
                                Runnable saver, Locale locale) {
        boolean success = false;
        try {
            saver.run();
            success = true;
        } catch (NotUniqueException e) {
            FieldError fieldError = new FieldError("user", "login",
                    messageSource.getMessage("user.exception.not.unique.login",
                            new String[]{user.getLogin()}, locale));
            bindingResult.addError(fieldError);
        }
        return success;
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
        user.setPassword("");
        return new ModelAndView("user/edit", "user", user);
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET,
            params = "action=changePassword")
    public ModelAndView showPasswordChangePage(@PathVariable Integer id) {
        PasswordWrapper wrapper = new PasswordWrapper();
        wrapper.setOwnerId(id);
        return new ModelAndView("user/changePassword", "wrapper", wrapper);
    }
}
