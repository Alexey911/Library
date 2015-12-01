package com.zhytnik.library.web;

import com.zhytnik.library.domain.Publisher;
import com.zhytnik.library.security.MinAccessed;
import com.zhytnik.library.service.PublisherService;
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
import java.util.Locale;

import static com.zhytnik.library.security.UserRole.LIBRARIAN;
import static com.zhytnik.library.security.UserRole.USER;

@Controller
public class PublisherController {
    @Autowired
    @Qualifier("publisherService")
    private PublisherService service;

    @Autowired
    private MessageSource messageSource;

    public void setPublisherService(PublisherService publisherService) {
        this.service = publisherService;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/publishers", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("publisher/showAll", "publishers", service.getAll());
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("publisher/show", "publisher", service.findById(id));
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/publishers/";
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers", method = RequestMethod.POST)
    public String add(@ModelAttribute("publisher") @Valid Publisher publisher,
                      BindingResult bindingResult, Locale locale) {
        return trySaveAndShowPage(publisher, bindingResult, locale,
                () -> service.add(publisher), "publisher/add");
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers/update", method = RequestMethod.POST)
    public String updateInPostMethod(@ModelAttribute("publisher") @Valid Publisher publisher,
                                     BindingResult bindingResult, Locale locale) {
        return trySaveAndShowPage(publisher, bindingResult, locale,
                () -> service.update(publisher), "publisher/edit");
    }

    private String trySaveAndShowPage(Publisher publisher, BindingResult bindingResult,
                                      Locale locale, Runnable verifyAndSave, String errorPage) {
        if (bindingResult.hasErrors()) {
            return errorPage;
        }
        if (publisher.getName().trim().isEmpty()) {
            FieldError fieldError = new FieldError("publisher", "name",
                    messageSource.getMessage("publisher.exception.not.set.name",
                            new String[]{publisher.getName()}, locale));
            bindingResult.addError(fieldError);
            return errorPage;
        }
        try {
            verifyAndSave.run();
            return "redirect:/publishers/";
        } catch (NotUniqueException e) {
            FieldError fieldError = new FieldError("publisher", "name",
                    messageSource.getMessage("publisher.exception.non.unique.name",
                            new String[]{publisher.getName()}, locale));
            bindingResult.addError(fieldError);
            return errorPage;
        }
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        return new ModelAndView("publisher/edit", "publisher", service.findById(id));
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers/add", method = RequestMethod.GET)
    public ModelAndView showAddPage() {
        return new ModelAndView("publisher/add", "publisher", service.create());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        return new ModelAndView("error", "errMsg", e.getMessage());
    }
}