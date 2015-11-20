package com.zhytnik.library.web;

import com.zhytnik.library.model.Category;
import com.zhytnik.library.service.CategoryService;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CategoryController {
    @Autowired
    @Qualifier("categoryService")
    private CategoryService service;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView getCategories() {
        return new ModelAndView("category/showAll", "categories", service.getAll());
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String getCategory(@PathVariable Integer id, Model model) {
        model.addAttribute("category", service.findById(id));
        return "category/showOne";
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(@ModelAttribute("Category") Category category,
                                 @PathVariable Integer id) {
        category.setId(id);
        service.delete(category);
        return "category/showAll";
    }

    // Without REST

/*    @RequestMapping(value = "/categories/save", method = RequestMethod.PUT)
    public ModelAndView deleteCategory(@ModelAttribute("Category") Category category) {
        return new ModelAndView("category/save", "category", category);
    }*/

    @RequestMapping(value = "/categories/save", method = RequestMethod.GET)
    public String showSaveCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "category/save";
    }

    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute("category") @Valid Category category,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/save";
        }
        service.add(category);
        return "redirect:/categories/";
    }

    @ExceptionHandler(NotUniqueException.class)
    public ModelAndView handleNotUniqueException(NotUniqueException e) {
        return new ModelAndView("category/errorPage", "errMsg", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        return new ModelAndView("category/errorPage", "errMsg", e.getMessage());
    }
}