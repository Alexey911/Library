package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.BookDao;
import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.domain.Book;
import com.zhytnik.library.domain.Category;
import com.zhytnik.library.domain.Publisher;

import java.util.Set;

public class BookDaoImpl implements BookDao {
    @Override
    public Set<Book> findBooksInPublisherCategories(Publisher publisher, Set<Category> categories) {
        return null;
    }

    @Override
    public Book persist(Book object) throws DaoException {
        return null;
    }

    @Override
    public Book findById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public void update(Book object) throws DaoException {

    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public Set<Book> getAll() throws DaoException {
        return null;
    }
}
