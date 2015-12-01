package com.zhytnik.library.service;

import com.zhytnik.library.dao.BookDao;
import com.zhytnik.library.domain.Book;

import static java.util.Objects.isNull;

public class BookService extends Service<Book> {
    @Override
    public Book create() {
        return new Book();
    }

    @Override
    protected void prepare(Book b) {
        b.setName(b.getName().trim());
        b.setAuthors(b.getAuthors().trim());
        if(!isNull(b.getAnnotation())) {
            b.setAnnotation(b.getAnnotation().trim());
        }
        super.prepare(b);
    }

    @Override
    public boolean isUnique(Book book) {
        return ((BookDao) getDao()).hasUniqueName(book);
    }
}