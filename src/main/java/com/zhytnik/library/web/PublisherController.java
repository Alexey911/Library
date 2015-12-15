package com.zhytnik.library.web;

import com.zhytnik.library.domain.Publisher;
import com.zhytnik.library.security.MinAccessed;
import com.zhytnik.library.service.PublisherService;
import com.zhytnik.library.service.exception.DeleteAssociatedObjectException;
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

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers", method = RequestMethod.GET, params = "page=add")
    public ModelAndView showAddPage() {
        return new ModelAndView("publisher/add", "publisher", service.create());
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers", method = RequestMethod.POST)
    public String add(@ModelAttribute("publisher") @Valid Publisher publisher,
                      BindingResult bindingResult, Locale locale) {
        if (!checkErrorAndTrySave(publisher, bindingResult,
                locale, () -> service.add(publisher))) {
            return "publisher/add";
        }
        return "redirect:/publishers";
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("publisher/show", "publisher", service.findById(id));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/publishers", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("publisher/showAll", "publishers", service.getAll());
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET,
            params = "page=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        return new ModelAndView("publisher/edit", "publisher", service.findById(id));
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("publisher") @Valid Publisher publisher,
                         BindingResult bindingResult, Locale locale) {
        if (!checkErrorAndTrySave(publisher, bindingResult,
                locale, () -> service.update(publisher))) {
            return "publisher/edit";
        }
        return "redirect:/publishers/" + publisher.getId();
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/publishers/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/publishers/";
    }

    private boolean checkErrorAndTrySave(Publisher publisher, BindingResult bindingResult,
                                         Locale locale, Runnable saver) {
        return !bindingResult.hasErrors() &&
                isNameFilled(publisher, bindingResult, locale) &&
                trySavePublisher(publisher, bindingResult, saver, locale);
    }

    private boolean isNameFilled(Publisher publisher, BindingResult bindingResult, Locale locale) {
        boolean filled = true;
        if (publisher.getName().trim().isEmpty()) {
            FieldError fieldError = new FieldError("publisher", "name",
                    messageSource.getMessage("publisher.exception.not.set.name",
                            new String[]{publisher.getName()}, locale));
            bindingResult.addError(fieldError);
            filled = false;
        }
        return filled;
    }

    private boolean trySavePublisher(Publisher publisher, BindingResult bindingResult,
                                     Runnable saver, Locale locale) {
        boolean success = false;
        try {
            saver.run();
            success = true;
        } catch (NotUniqueException e) {
            FieldError fieldError = new FieldError("publisher", "name",
                    messageSource.getMessage("publisher.exception.not.unique.name",
                            new String[]{publisher.getName()}, locale));
            bindingResult.addError(fieldError);
        }
        return success;
    }

    @ExceptionHandler(DeleteAssociatedObjectException.class)
    public ModelAndView handleDeleteFail(Locale locale) {
        String message = messageSource.getMessage("exception.delete.associated.publisher",
                new String[]{}, locale);
        return new ModelAndView("error", "errMsg", message);
    }
}
