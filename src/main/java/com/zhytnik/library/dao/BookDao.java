package com.zhytnik.library.dao;

import com.zhytnik.library.domain.Book;

import java.util.Set;

public interface BookDao extends GenericDao<Book> {
    Set<Book> findBooksInPublisherCategories(Integer publisher,
                                             Set<Integer> categories) throws DaoException;

    Set<Book> findBooksByCategories(Set<Integer> categories) throws DaoException;

    boolean hasUniqueName(Book book) throws DaoException;

    Set<Book> getBooksInfo() throws DaoException;
}
