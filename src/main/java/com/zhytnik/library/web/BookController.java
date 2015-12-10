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
                               @RequestParam(value = "selected", required = false)
                               List<Integer> categories, Locale locale) {
        if (!trySaveAndShowPage(book, bindingResult, locale,
                () -> bookService.update(book), categories)) {
            ModelAndView view = getBasicModelAndView("book/edit");
            addSelected(view, categories);
            return view;
        }
        return new ModelAndView(new RedirectView("/books"));
    }

    @Secured("ROLE_LIBRARIAN")
    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET,
            params = "action=edit")
    public ModelAndView showEditPage(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        ModelAndView view = getBookModelAndView("book/edit", book);
        addSelected(view, getCategoryIds(book));
        return view;
    }

    private List<Integer> getCategoryIds(Book book) {
        return book.getCategories().stream().
                map(Category::getId).collect(Collectors.toList());
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books/add", method = RequestMethod.GET)
    public ModelAndView showAddPage() {
        return getBookModelAndView("book/add", bookService.create());
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET,
            params = {"action=search", "filter=publisher"})
    public ModelAndView searchByPublisher(@RequestParam(value = "publisher",
            required = false) Integer publisher) {
        ModelAndView view = new ModelAndView("book/searchByPublisher", "selectedId", publisher);
        addPublishers(view);
        return view.addObject("books", bookService.findByPublisher(publisher));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET,
            params = {"action=showSearchPage", "filter=publisher"})
    public ModelAndView showSearchByPublisherPage() {
        return new ModelAndView("book/searchByPublisher",
                "publishers", publisherService.getAll());
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET,
            params = {"action=search", "filter=category"})
    public ModelAndView searchByCategory(@RequestParam(value = "category",
            required = false) Integer category) {
        ModelAndView view = new ModelAndView("book/searchByCategory", "selectedId", category);
        addCategories(view);
        return view.addObject("books", bookService.findByCategory(category));
    }

    @MinAccessed(USER)
    @RequestMapping(value = "/books", method = RequestMethod.GET,
            params = {"action=showSearchPage", "filter=category"})
    public ModelAndView showSearchByCategoryPage() {
        return new ModelAndView("book/searchByCategory",
                "categories", categoryService.getAll());
    }

    @MinAccessed(LIBRARIAN)
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("book") @Valid Book book,
                            BindingResult bindingResult,
                            @RequestParam(value = "selected", required = false)
                            List<Integer> categories, Locale locale) {
        if (!trySaveAndShowPage(book, bindingResult, locale,
                () -> bookService.add(book), categories)) {
            ModelAndView view = getBasicModelAndView("book/add");
            addSelected(view, categories);
            return view;
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

    private ModelAndView getBookModelAndView(String view, Book book) {
        return getBasicModelAndView(view).addObject("book", book);
    }

    private ModelAndView getBasicModelAndView(String view) {
        ModelAndView v = new ModelAndView(view);
        addCategories(v);
        addPublishers(v);
        return v;
    }

    private void addPublishers(ModelAndView model) {
        model.addObject("publishers", publisherService.getAll());
    }

    private void addCategories(ModelAndView model) {
        model.addObject("categories", categoryService.getAll());
    }

    private void addSelected(ModelAndView model, List<Integer> selected) {
        model.addObject("selected", selected);
    }

}
