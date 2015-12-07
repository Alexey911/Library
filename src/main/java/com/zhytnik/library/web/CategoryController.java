package com.zhytnik.library.web;

import com.zhytnik.library.domain.Category;
import com.zhytnik.library.security.MinAccessed;
import com.zhytnik.library.service.CategoryService;
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
    @RequestMapping(value = "/categories/{id}/delete", method = RequestMethod.POST)
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
    public String update(@ModelAttribute("category") @Valid Category category,
                         BindingResult bindingResult, Locale locale) {
        return trySaveAndShowPage(category, bindingResult, locale,
                () -> service.update(category), "category/edit");
    }

    private String trySaveAndShowPage(Category category, BindingResult bindingResult,
                                      Locale locale, Runnable saver, String errorPage) {
        if (bindingResult.hasErrors()) {
            return errorPage;
        }
        if (!isNameFilled(category, bindingResult, locale)) {
            return errorPage;
        }
        if (!trySaveCategory(category, bindingResult, saver, locale)) {
            return errorPage;
        }
        return "redirect:/categories/";
    }

    private boolean trySaveCategory(Category category, BindingResult bindingResult,
                                    Runnable saver, Locale locale) {
        boolean success = false;
        try {
            saver.run();
            success = true;
        } catch (NotUniqueException e) {
            FieldError fieldError = new FieldError("category", "name",
                    messageSource.getMessage("category.exception.not.unique.name",
                            new String[]{category.getName()}, locale));
            bindingResult.addError(fieldError);
        }
        return success;
    }

    private boolean isNameFilled(Category category, BindingResult bindingResult, Locale locale) {
        boolean filled = true;
        if (category.getName().trim().isEmpty()) {
            FieldError fieldError = new FieldError("category", "name",
                    messageSource.getMessage("category.exception.not.set.name",
                            new String[]{category.getName()}, locale));
            bindingResult.addError(fieldError);
            filled = false;
        }
        return filled;
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

    //TODO
    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/categories/findByName", method = RequestMethod.GET)
    public
    @ResponseBody
    Category getCategoryByName(@RequestParam String name) {
        return service.findById(5);
    }

    @ExceptionHandler(DeleteAssociatedObjectException.class)
    public ModelAndView handleDeleteFail(Locale locale) {
        String message = messageSource.getMessage("exception.delete.associated.category",
                new String[]{}, locale);
        return new ModelAndView("error", "errMsg", message);
    }
}
