package com.zhytnik.library.web;

import com.zhytnik.library.model.Category;
import com.zhytnik.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView showCategories() {
        return new ModelAndView("category/showAll", "categories", categoryService.getAll());
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String showCategory(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "category/showOne";
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(@ModelAttribute("Category") Category category,
                                 @PathVariable Integer id) {
        category.setId(id);
        categoryService.delete(category);
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
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "category/save";
        }
        model.addAttribute("category", category);
        categoryService.add(category);
        return "redirect:/categories/";
    }
}