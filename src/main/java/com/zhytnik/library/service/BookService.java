package com.zhytnik.library.service;

import com.zhytnik.library.dao.BookDao;
import com.zhytnik.library.domain.Book;

import static java.util.Objects.isNull;

public class BookService extends Service<Book> {
    public BookService() {
        super();
    }

    @Override
    public Book create() {
        return new Book();
    }

    @Override
    public boolean isUnique(Book b) {
        String name = b.getName();
        BookDao dao = (BookDao) getDao();
        if (dao.isUniqueName(name)) {
            return true;
        }
        if (isNull(b.getId())) {
            return false;
        }
        Book daoItem = dao.findById(b.getId());
        return daoItem.getName().equals(name);
    }
}