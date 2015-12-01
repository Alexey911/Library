package com.zhytnik.library.web;

import com.zhytnik.library.domain.Category;
import com.zhytnik.library.security.MinAccessed;
import com.zhytnik.library.service.CategoryService;
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

    @MinAccessed(USER)
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("category/showAll", "categories", service.getAll());
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("category/show", "category", service.findById(id));
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/categories/";
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String add(@ModelAttribute("category") @Valid Category category,
                      BindingResult bindingResult, Locale locale) {
        return trySaveAndShowPage(category, bindingResult, locale,
                () -> service.add(category), "category/add");
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    public String updateInPostMethod(@ModelAttribute("category") @Valid Category category,
                                     BindingResult bindingResult, Locale locale) {
        return trySaveAndShowPage(category, bindingResult, locale,
                () -> service.update(category), "category/edit");
    }

    private String trySaveAndShowPage(Category category, BindingResult bindingResult,
                                      Locale locale, Runnable verifyAndSave, String errorPage) {
        if (bindingResult.hasErrors()) {
            return errorPage;
        }
        if (category.getName().trim().isEmpty()) {
            FieldError fieldError = new FieldError("category", "name",
                    messageSource.getMessage("category.exception.not.set.name",
                            new String[]{category.getName()}, locale));
            bindingResult.addError(fieldError);
            return errorPage;
        }
        try {
            verifyAndSave.run();
            return "redirect:/categories/";
        } catch (NotUniqueException e) {
            FieldError fieldError = new FieldError("category", "name",
                    messageSource.getMessage("category.exception.non.unique.name",
                            new String[]{category.getName()}, locale));
            bindingResult.addError(fieldError);
            return errorPage;
        }
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        return new ModelAndView("category/edit", "category", service.findById(id));
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/categories/add", method = RequestMethod.GET)
    public ModelAndView showAddPage() {
        return new ModelAndView("category/add", "category", service.create());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        return new ModelAndView("error", "errMsg", e);
    }
}