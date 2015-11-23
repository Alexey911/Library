package com.zhytnik.library.web;

import com.google.common.base.Objects;
import com.zhytnik.library.model.Category;
import com.zhytnik.library.service.CategoryService;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    //REST start

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView get() {
        return new ModelAndView("category/showAll", "categories", service.getAll());
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ModelAndView getAll(@PathVariable Integer id,
                               @RequestParam(required = false) String action) {
        if (Objects.equal(action, "edit")) {
            return new ModelAndView("category/edit", "category", service.findById(id));
        }
        return new ModelAndView("category/show", "category", service.findById(id));
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public String delete(@ModelAttribute("Category") Category category,
                         @PathVariable Integer id) {
        category.setId(id);
        service.delete(category);
        return "redirect:/categories/";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String add(@ModelAttribute("category") @Valid Category category,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/add";
        }
        service.add(category);
        return "redirect:/categories/";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.PUT)
    public String update(@ModelAttribute("category") @Valid Category category,
                         @RequestParam(value = "id", required = false) Integer id,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/edit";
        }
        category.setId(id);
        service.update(category);
        return "redirect:/categories/";
    }

    //REST end

    @RequestMapping(value = "/categories/add", method = RequestMethod.GET)
    public String showAddPage(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @ExceptionHandler(NotUniqueException.class)
    public ModelAndView handleNotUniqueException(NotUniqueException e, Locale locale) {
        String msg = messageSource.getMessage("category.not_unique",
                new Object[]{e.getDescription()}, locale);
        return new ModelAndView("category/error", "errMsg", msg);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException() {
        return new ModelAndView("category/error", "errMsg", "Oops!!!");
    }
}