package com.zhytnik.library.web;

import com.zhytnik.library.domain.Category;
import com.zhytnik.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class CategoryController {
    @Autowired
    @Qualifier("categoryService")
    private CategoryService service;

    @Autowired
    private MessageSource messageSource;

    public void setCategoryService(CategoryService categoryService) {
        this.service = categoryService;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("category/showAll", "categories", service.getAll());
    }

    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("category/show", "category", service.findById(id));
    }

    @Secured("ROLE_LIBRARIAN")
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public String delete(@ModelAttribute("category") Category category,
                         @PathVariable Integer id) {
        category.setId(id);
        service.delete(category.getId());
        return "redirect:/categories/";
    }

    @Secured("ROLE_LIBRARIAN")
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String add(@ModelAttribute("category") @Valid Category category,
                      BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors() || hasErrors(category, bindingResult, locale)) {
            return "category/add";
        }
        service.add(category);
        return "redirect:/categories/";
    }

    @Secured("ROLE_LIBRARIAN")
    @RequestMapping(value = "/categories", method = RequestMethod.PUT)
    public String update(@ModelAttribute("category") @Valid Category category) {
        service.update(category);
        return "redirect:/categories/";
    }

    private boolean hasErrors(Category category, BindingResult bindingResult, Locale locale) {
        if (category.getName().trim().length() == 0) {
            FieldError fieldError = new FieldError("category", "name",
                    messageSource.getMessage("category.exception.not.set.name",
                            new String[]{category.getName()}, locale));
            bindingResult.addError(fieldError);
            return true;
        }
        boolean isUnique = service.isUnique(category);
        if (!isUnique) {
            FieldError fieldError = new FieldError("category", "name",
                    messageSource.getMessage("category.exception.non.unique.name",
                            new String[]{category.getName()}, locale));
            bindingResult.addError(fieldError);
        }
        return !isUnique;
    }

    @Secured("ROLE_LIBRARIAN")
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        return new ModelAndView("category/edit", "category", service.findById(id));
    }

    @Secured("ROLE_LIBRARIAN")
    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    public String updateInPostMethod(@ModelAttribute("category") @Valid Category category,
                                     BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors() || !hasErrors(category, bindingResult, locale)) {
            return "category/edit";
        }
        service.update(category);
        return "redirect:/categories/";
    }

    @Secured("ROLE_LIBRARIAN")
    @RequestMapping(value = "/categories/add", method = RequestMethod.GET)
    public String showAddPage(Model model) {
        model.addAttribute("category", service.create());
        return "category/add";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        return new ModelAndView("category/error", "errMsg", e);
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ModelAndView handleAccessDenied() {
        return new ModelAndView("login", "msg", "Access Denied");
    }
}