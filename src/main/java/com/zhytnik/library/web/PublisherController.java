package com.zhytnik.library.web;

import com.zhytnik.library.domain.Publisher;
import com.zhytnik.library.service.PublisherService;
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

    @RequestMapping(value = "/publishers", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("publisher/showAll", "publishers", service.getAll());
    }

    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("publisher/show", "publisher", service.findById(id));
    }

    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.DELETE)
    public String delete(@ModelAttribute("publisher") Publisher publisher,
                         @PathVariable Integer id) {
        publisher.setId(id);
        service.delete(publisher.getId());
        return "redirect:/publishers/";
    }

    @RequestMapping(value = "/publishers", method = RequestMethod.POST)
    public String add(@ModelAttribute("publisher") @Valid Publisher publisher,
                      BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors() || !isUnique(publisher, bindingResult, locale)) {
            return "publisher/add";
        }
        service.add(publisher);
        return "redirect:/publishers/";
    }

    @RequestMapping(value = "/publishers", method = RequestMethod.PUT)
    public String update(@ModelAttribute("publisher") @Valid Publisher publisher,
                         BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors() || !isUnique(publisher, bindingResult, locale)) {
            return "publisher/edit";
        }
        service.update(publisher);
        return "redirect:/publishers/";
    }

    private boolean isUnique(Publisher publisher, BindingResult bindingResult, Locale locale) {
        boolean isUnique = service.isUnique(publisher);
        if (!isUnique) {
            FieldError fieldError = new FieldError("publisher", "name",
                    messageSource.getMessage("category.exception.non.unique.name",
                            new String[]{publisher.getName()}, locale));
            bindingResult.addError(fieldError);
        }
        return isUnique;
    }

    @RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        return new ModelAndView("publisher/edit", "publisher", service.findById(id));
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    public String updateInPostMethod(@ModelAttribute("publisher") @Valid Publisher publisher,
                                     BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors() || !isUnique(publisher, bindingResult, locale)) {
            return "publisher/edit";
        }
        service.update(publisher);
        return "redirect:/publishers/";
    }

    @RequestMapping(value = "/publishers/add", method = RequestMethod.GET)
    public String showAddPage(Model model) {
        model.addAttribute("publisher", service.create());
        return "publisher/add";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        return new ModelAndView("publisher/error", "errMsg", e.getMessage());
    }
}