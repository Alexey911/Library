package com.zhytnik.library.dao.jdbc.mysql.dependence;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.jdbc.Dependence;
import com.zhytnik.library.dao.jdbc.mysql.BookDaoImpl;
import com.zhytnik.library.dao.jdbc.mysql.PublisherDaoImpl;
import com.zhytnik.library.domain.Book;
import com.zhytnik.library.domain.Publisher;

import java.sql.Connection;

import static java.util.Objects.isNull;

public class BookToPublisherDependence extends Dependence<Book> {
    public BookToPublisherDependence() {
    }

    @Override
    protected void update(Book book, Connection connection) throws DaoException {
        Publisher p = book.getPublisher();
        if (isNull(p) || isNull(p.getId())) {
            return;
        }

        PublisherDaoImpl dao = null;//(PublisherDaoImpl) getContext().getBean("publisherDao");

        Publisher copy = dao.findById(p.getId());
        p.setName(copy.getName());
        p.setAddress(copy.getAddress());
    }

    @Override
    protected void delete(Book book, Connection connection) {
        book.setPublisher(null);

        BookDaoImpl dao = null;// (BookDaoImpl) getContext().getBean("bookDao");

        dao.update(book);
    }
}