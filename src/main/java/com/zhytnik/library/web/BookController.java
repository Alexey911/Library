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
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.zhytnik.library.security.UserRole.LIBRARIAN;
import static com.zhytnik.library.security.UserRole.USER;
import static java.util.Objects.isNull;

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
        return new ModelAndView("book/showAll", "books", bookService.getBooksInfo());
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
    public ModelAndView update(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult,
                               @RequestParam(value = "newCategories", required = false)
                               List<Integer> categories, Locale locale) {
        if (!trySaveAndShowPage(book, bindingResult, locale,
                () -> bookService.update(book), categories)) {
            return getModelAndView("book/edit", null);
        }
        return new ModelAndView(new RedirectView("/books"));
    }

    @Secured("ROLE_LIBRARIAN")
    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        return getModelAndView("book/edit", bookService.findById(id));
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/add", method = RequestMethod.GET)
    public ModelAndView showAddPage() {
        return getModelAndView("book/add", bookService.create());
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("book") @Valid Book book,
                            BindingResult bindingResult,
                            @RequestParam(value = "newCategories", required = false)
                            List<Integer> categories, Locale locale) {
        if (!trySaveAndShowPage(book, bindingResult, locale,
                () -> bookService.add(book), categories)) {
            return getModelAndView("book/add", null);
        }
        return new ModelAndView(new RedirectView("/books"));
    }

    private boolean trySaveAndShowPage(Book book, BindingResult bindingResult,
                                       Locale locale, Runnable saver, List<Integer> categories) {
        boolean valid = !bindingResult.hasErrors() &&
                isDataFilled(book, bindingResult, locale);
        if (!valid) {
            return false;
        }
        book.setCategories(convertCategoryIdList(categories));
        return trySaveBook(book, bindingResult, saver, locale);
    }

    private boolean trySaveBook(Book book, BindingResult bindingResult,
                                Runnable saver, Locale locale) {
        boolean success = false;
        try {
            saver.run();
            success = true;
        } catch (NotUniqueException e) {
            FieldError fieldError = new FieldError("book", "name",
                    messageSource.getMessage("book.exception.not.unique.name",
                            new String[]{book.getName()}, locale));
            bindingResult.addError(fieldError);
        }
        return success;
    }

    private boolean isDataFilled(Book book, BindingResult bindingResult, Locale locale) {
        boolean filled = true;
        if (book.getName().trim().isEmpty()) {
            FieldError fieldError = new FieldError("book", "name",
                    messageSource.getMessage("book.exception.not.set.name",
                            new String[]{book.getName()}, locale));
            bindingResult.addError(fieldError);
            filled = false;
        }
        if (isNull(book.getPublisher()) || isNull(book.getPublisher().getId())) {
            FieldError fieldError = new FieldError("book", "publisher",
                    messageSource.getMessage("book.exception.not.set.publisher",
                            new String[]{book.getName()}, locale));
            bindingResult.addError(fieldError);
            filled = false;
        }
        return filled;
    }

    private Set<Category> convertCategoryIdList(List<Integer> categories) {
        Set<Category> result = new HashSet<>();
        if (!isNull(categories)) {
            for (Integer id : categories) {
                Category category = new Category();
                category.setId(id);
                result.add(category);
            }
        }
        return result;
    }

    private ModelAndView getModelAndView(String view, Book book) {
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject("publishers", publisherService.getAll()).
                addObject("categories", categoryService.getAll());
        if (!isNull(book)) {
            modelAndView.addObject("book", book);
        }
        return modelAndView;
    }
}
