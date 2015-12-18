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
import java.util.stream.Collectors;

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

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books", method = RequestMethod.GET, params = "page=add")
    public ModelAndView showAddPage() {
        return getBookModelAndView("book/add", bookService.create());
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("book") @Valid Book book,
                            BindingResult bindingResult,
                            @RequestParam(value = "selected", required = false)
                            List<Integer> categories, Locale locale) {
        if (!checkErrorAndTrySave(book, bindingResult, locale,
                () -> bookService.add(book), categories)) {
            return addSelected(getFilledModelAndView("book/add"), categories);
        }
        return new ModelAndView(new RedirectView("books"));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) {
        return new ModelAndView("book/show", "book", bookService.findById(id));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("book/showAll", "books", bookService.getBooksInfo());
    }

    @Secured("ROLE_LIBRARIAN")
    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET,
            params = "page=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        List<Integer> categories = getCategoryIds(book);
        return addSelected(getBookModelAndView("book/edit", book), categories);
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult,
                               @RequestParam(value = "selected", required = false)
                               List<Integer> categories, Locale locale) {
        if (!checkErrorAndTrySave(book, bindingResult, locale,
                () -> bookService.update(book), categories)) {
            return addSelected(getFilledModelAndView("book/edit"), categories);
        }
        return new ModelAndView(new RedirectView(Integer.toString(book.getId())));
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        bookService.delete(id);
        return "redirect:/books/";
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET,
            params = {"page=search", "filter=publisher"})
    public ModelAndView showSearchByPublisherPage() {
        return addPublishers(new ModelAndView("book/searchByPublisher"));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET,
            params = {"action=search", "filter=publisher"})
    public ModelAndView searchByPublisher(@RequestParam(value = "publisher", required = false) Integer publisher) {
        ModelAndView view = new ModelAndView("book/searchByPublisher", "selectedId", publisher);
        return addPublishers(view).addObject("books", bookService.findByPublisher(publisher));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET,
            params = {"page=search", "filter=category"})
    public ModelAndView showSearchByCategoryPage() {
        return addCategories(new ModelAndView("book/searchByCategory"));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET,
            params = {"action=search", "filter=category"})
    public ModelAndView searchByCategory(@RequestParam(value = "category",
            required = false) Integer category) {
        ModelAndView view = new ModelAndView("book/searchByCategory", "selectedId", category);
        return addCategories(view).addObject("books", bookService.findByCategory(category));
    }

    private List<Integer> getCategoryIds(Book book) {
        Set<Category> categories = book.getCategories();
        return categories.stream().map(Category::getId).collect(Collectors.toList());
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

    private boolean checkErrorAndTrySave(Book book, BindingResult bindingResult,
                                         Locale locale, Runnable saver, List<Integer> categories) {
        boolean valid = !bindingResult.hasErrors() &&
                isDataFilled(book, bindingResult, locale);
        if (!valid) {
            return false;
        }
        book.setCategories(convertCategoryIdList(categories));
        return trySaveBook(book, bindingResult, saver, locale);
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

    private ModelAndView getBookModelAndView(String view, Book book) {
        return getFilledModelAndView(view).addObject("book", book);
    }

    private ModelAndView getFilledModelAndView(String view) {
        return addPublishers(addCategories(new ModelAndView(view)));
    }

    private ModelAndView addPublishers(ModelAndView model) {
        return model.addObject("publishers", publisherService.getAll());
    }

    private ModelAndView addCategories(ModelAndView model) {
        return model.addObject("categories", categoryService.getAll());
    }

    private ModelAndView addSelected(ModelAndView model, List<Integer> selected) {
        return model.addObject("selected", selected);
    }
}
