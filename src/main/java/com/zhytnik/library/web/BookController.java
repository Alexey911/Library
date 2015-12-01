package com.zhytnik.library.web;

import com.zhytnik.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
    @Autowired
    @Qualifier("bookService")
    private BookService service;

    public void setService(BookService bookService) {
        service = bookService;
    }

    @Secured({"ROLE_USER", "ROLE_LIBRARIAN"})
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView getAll() {
        return new ModelAndView("book/show", "books", service.getAll());
    }
}
