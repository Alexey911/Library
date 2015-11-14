package com.zhytnik.library.dao.jdbc.mysql.dependence;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.DaoFactory;
import com.zhytnik.library.dao.jdbc.Dependence;
import com.zhytnik.library.dao.jdbc.mysql.BookDao;
import com.zhytnik.library.dao.jdbc.mysql.PublisherDao;
import com.zhytnik.library.entity.Book;
import com.zhytnik.library.entity.Publisher;

import java.sql.Connection;

import static java.util.Objects.isNull;

public class BookToPublisherDependence extends Dependence<Book> {
    public BookToPublisherDependence(DaoFactory factory) {
        super(factory);
    }

    @Override
    protected void update(Book book, Connection connection) throws DaoException {
        Publisher p = book.getPublisher();
        if (isNull(p) || isNull(p.getId())) {
            return;
        }
        PublisherDao dao = (PublisherDao) getDaoFactory().getDao(Publisher.class);
        Publisher copy = dao.findById(p.getId());
        p.setName(copy.getName());
        p.setAddress(copy.getAddress());
    }

    @Override
    protected void delete(Book book, Connection connection) {
        book.setPublisher(null);
        BookDao dao = (BookDao) getDaoFactory().getDao(Book.class);
        dao.update(book);
    }
}