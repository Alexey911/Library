package com.zhytnik.library.service;

import com.zhytnik.library.dao.BookDao;
import com.zhytnik.library.domain.Book;

import java.util.List;

import static java.util.Objects.isNull;

public class BookService extends AbstractService<Book> {
    @Override
    public Book create() {
        return new Book();
    }

    @Override
    protected void prepare(Book b) {
        b.setName(b.getName().trim());
        b.setAuthors(b.getAuthors().trim());
        if (!isNull(b.getAnnotation())) {
            b.setAnnotation(b.getAnnotation().trim());
        }
    }

    @Override
    public boolean isUnique(Book book) {
        return getBookDao().hasUniqueName(book);
    }

    public List<Book> getBooksInfo() {
        return getBookDao().getBooksInfo();
    }

    private BookDao getBookDao() {
        return (BookDao) getDao();
    }

    public List<Book> findByPublisher(Integer publisher) {
        if (isNull(publisher)) {
            return getBooksInfo();
        }
        return getBookDao().findByPublisher(publisher);
    }

    public List<Book> findByCategory(Integer category) {
        if (isNull(category)) {
            return getBooksInfo();
        }
        return getBookDao().findByCategory(category);
    }
}
