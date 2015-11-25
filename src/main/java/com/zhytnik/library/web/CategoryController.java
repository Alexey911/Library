package com.zhytnik.library.web;

import com.zhytnik.library.domain.Category;
import com.zhytnik.library.service.CategoryService;
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

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("category/showAll", "categories", service.getAll());
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("category/show", "category", service.findById(id));
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public String delete(@ModelAttribute("category") Category category,
                         @PathVariable Integer id) {
        category.setId(id);
        service.delete(category);
        return "redirect:/categories/";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String add(@ModelAttribute("category") @Valid Category category,
                      BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors() || !isUnique(category, bindingResult, locale)) {
            return "category/add";
        }
        service.add(category);
        return "redirect:/categories/";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.PUT)
    public String update(@ModelAttribute("category") @Valid Category category) {
        service.update(category);
        return "redirect:/categories/";
    }

    private boolean isUnique(Category category, BindingResult bindingResult, Locale locale) {
        boolean isUnique = service.isUnique(category);
        if (!isUnique) {
            FieldError fieldError = new FieldError("category", "name",
                    messageSource.getMessage("category.exception.non.unique.name",
                            new String[]{category.getName()}, locale));
            bindingResult.addError(fieldError);
        }
        return isUnique;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        return new ModelAndView("category/edit", "category", service.findById(id));
    }

    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    public String updateInPostMethod(@ModelAttribute("category") @Valid Category category,
                                     BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors() || !isUnique(category, bindingResult, locale)) {
            return "category/edit";
        }
        service.update(category);
        return "redirect:/categories/";
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.GET)
    public String showAddPage(Model model) {
        model.addAttribute("category", service.create());
        return "category/add";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        return new ModelAndView("category/error", "errMsg", e.getMessage());
    }
}