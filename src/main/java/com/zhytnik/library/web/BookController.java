package com.zhytnik.library.web;

import com.zhytnik.library.domain.Book;
import com.zhytnik.library.domain.Category;
import com.zhytnik.library.security.MinAccessed;
import com.zhytnik.library.service.BookService;
import com.zhytnik.library.service.CategoryService;
import com.zhytnik.library.service.PublisherService;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static com.zhytnik.library.security.UserRole.LIBRARIAN;
import static com.zhytnik.library.security.UserRole.USER;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("book/showAll", "books", bookService.getAll());
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable Integer id) {
        bookService.delete(id);
        return "redirect:/books/";
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("book/show", "book", bookService.findById(id));
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @RequestParam(value = "newCategories", required = false) List<Integer> categories,
                         Locale locale) {
        book.setPublisher(publisherService.findById(book.getPublisher().getId()));
        Set<Category> newCategories = categories.stream().filter(x -> x != null).map(categoryService::findById).collect(Collectors.toSet());
        book.setCategories(newCategories);
        return trySaveAndShowPage(book, bindingResult, locale,
                () -> bookService.update(book), "book/edit");
    }

    private String trySaveAndShowPage(Book book, BindingResult bindingResult,
                                      Locale locale, Runnable verifyAndSave, String errorPage) {
        if (bindingResult.hasErrors()) {
            return errorPage;
        }
        if (book.getName().trim().isEmpty()) {
            FieldError fieldError = new FieldError("book", "name",
                    messageSource.getMessage("book.exception.not.set.name",
                            new String[]{book.getName()}, locale));
            bindingResult.addError(fieldError);
            return errorPage;
        }
        try {
            verifyAndSave.run();
            return "redirect:/books/";
        } catch (NotUniqueException e) {
            FieldError fieldError = new FieldError("book", "name",
                    messageSource.getMessage("book.exception.not.unique.name",
                            new String[]{book.getName()}, locale));
            bindingResult.addError(fieldError);
            return errorPage;
        }
    }

    @Secured("ROLE_LIBRARIAN")
    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("book/edit");
        modelAndView.addObject("book", bookService.findById(id)).
                addObject("publishers", publisherService.getAll())
                .addObject("newCategories", new ArrayList<>(categoryService.getAll()));
        return modelAndView;
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/add", method = RequestMethod.GET)
    public ModelAndView showAddPage() {
        ModelAndView modelAndView = new ModelAndView("book/add");
        modelAndView.addObject("book", bookService.create()).
                addObject("publishers", publisherService.getAll())
                .addObject("newCategories", new ArrayList<>(categoryService.getAll()));
        return modelAndView;
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String add(@ModelAttribute("book") @Valid Book book,
                      BindingResult bindingResult,
                      @RequestParam(value = "newCategories", required = false) List<Integer> categories,
                      Locale locale) {
        book.setPublisher(publisherService.findById(book.getPublisher().getId()));
        Set<Category> newCategories = categories.stream().filter(x -> x != null).map(categoryService::findById).collect(Collectors.toSet());
        book.setCategories(newCategories);
        return trySaveAndShowPage(book, bindingResult, locale,
                () -> bookService.add(book), "book/add");
    }
}
