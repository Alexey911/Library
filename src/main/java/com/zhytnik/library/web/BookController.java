package com.zhytnik.library.web;

import com.zhytnik.library.domain.Book;
import com.zhytnik.library.security.MinAccessed;
import com.zhytnik.library.service.BookService;
import com.zhytnik.library.service.CategoryService;
import com.zhytnik.library.service.PublisherService;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

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

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("book/showAll", "books", bookService.getAll());
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("book/show", "book", bookService.findById(id));
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, Locale locale) {
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
            return "redirect:/categories/";
        } catch (NotUniqueException e) {
            FieldError fieldError = new FieldError("book", "name",
                    messageSource.getMessage("book.exception.non.unique.name",
                            new String[]{book.getName()}, locale));
            bindingResult.addError(fieldError);
            return errorPage;
        }
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        return new ModelAndView("book/edit", "book", bookService.findById(id));
    }
}
