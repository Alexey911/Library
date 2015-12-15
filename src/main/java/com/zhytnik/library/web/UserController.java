package com.zhytnik.library.web;

import com.zhytnik.library.domain.User;
import com.zhytnik.library.security.Accessed;
import com.zhytnik.library.security.MinAccessed;
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

import static com.zhytnik.library.security.UserRole.ADMIN;
import static com.zhytnik.library.security.UserRole.USER;

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
    @RequestMapping(value = "/users", method = RequestMethod.GET, params = "page=add")
    public ModelAndView showAddPage() {
        return new ModelAndView("user/add", "user", service.create());
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") @Valid User user,
                      BindingResult bindingResult, Locale locale) {
        if (!checkErrorAndTrySave(user, bindingResult,
                () -> service.addConfirmedUser(user), locale)) {
            return "user/add";
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage() {
        return new ModelAndView("register", "user", service.create());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult, Locale locale) {
        if (!checkErrorAndTrySave(user, bindingResult,
                () -> service.add(user), locale)) {
            return "register";
        }
        return "redirect:/login";
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("user/show", "user", service.findById(id));
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("user/showAll", "users", service.getAll());
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users", method = RequestMethod.GET, params = "filter=me")
    public ModelAndView showUserPage(Principal principal) {
        return new ModelAndView("user/show", "user", service.findByName(principal.getName()));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, params = "page=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        return new ModelAndView("user/edit", "user", service.findById(id));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public String updateInfo(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, Principal principal,
                             Locale locale, HttpServletRequest request) {
        if (bindingResult.getErrorCount() > 0) {
            return "user/edit";
        }
        if (!isLoginFilled(user, bindingResult, locale)) {
            return "user/edit";
        }
        if (!trySaveUser(user, bindingResult, () -> service.updateLoginRole(user), locale)) {
            return "user/edit";
        }
        if (isOwner(user, principal)) {
            logout(request);
            return "redirect:/login";
        }
        return "redirect:/users";
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET,
            params = {"page=edit", "field=password"})
    public ModelAndView showPasswordChangePage(@PathVariable Integer id) {
        PasswordWrapper wrapper = new PasswordWrapper();
        wrapper.setOwnerId(id);
        return new ModelAndView("user/changePassword", "wrapper", wrapper);
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/updatePassword", method = RequestMethod.POST)
    public String updatePassword(@ModelAttribute("wrapper") @Valid PasswordWrapper wrapper,
                                 BindingResult bindingResult, Principal principal,
                                 Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "user/changePassword";
        }
        Integer id = wrapper.getOwnerId();
        try {
            service.updatePassword(id, wrapper.getLastPassword(), wrapper.getNewPassword());
        } catch (PasswordMismatchException e) {
            FieldError fieldError = new FieldError("wrapper", "lastPassword",
                    messageSource.getMessage("password.exception.password.mismatch",
                            new String[]{}, locale));
            bindingResult.addError(fieldError);
            return "user/changePassword";
        }
        if (isOwner(service.findById(id), principal)) {
            logout(request);
            return "redirect:/login";
        }
        return "redirect:/users";
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users", method = RequestMethod.GET,
            params = "filter=notConfirmed")
    public ModelAndView showNotConfirmedUsers() {
        return new ModelAndView("user/confirm", "users", service.getUnconfirmedUsers());
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users", method = RequestMethod.PUT, params = "action=confirm")
    public String confirm(@RequestParam(value = "users", required = false) List<Integer> users) {
        service.confirm(users);
        return "redirect:/users/";
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, params = "action=confirm")
    public String confirm(@PathVariable("id") Integer id) {
        service.confirm(id);
        return "redirect:/users/";
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, params = "action=resetConfirm")
    public String resetConfirm(@PathVariable("id") Integer id) {
        service.resetConfirm(id);
        return "redirect:/users/";
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id,
                         Principal principal, HttpServletRequest request) {
        User user = service.findById(id);
        service.delete(id);
        if (isOwner(user, principal)) {
            logout(request);
            return "redirect:/login";
        }
        return "redirect:/users";
    }

    private boolean isOwner(User user, Principal principal) {
        return user.getLogin().equals(principal.getName());
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, params = "action=disable")
    public String block(@PathVariable Integer id) {
        service.disable(id);
        return "redirect:/users/";
    }

    @Accessed(ADMIN)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, params = "action=activate")
    public String activate(@PathVariable Integer id) {
        service.activate(id);
        return "redirect:/users/";
    }

    private boolean checkErrorAndTrySave(User user, BindingResult bindingResult,
                                         Runnable saver, Locale locale) {
        return !bindingResult.hasErrors() &&
                isLoginFilled(user, bindingResult, locale) &&
                trySaveUser(user, bindingResult, saver, locale);
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

    private void logout(HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, null, null);
    }
}
