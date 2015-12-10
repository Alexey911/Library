package com.zhytnik.library.dao;

import com.zhytnik.library.domain.Book;

import java.util.List;

public interface BookDao extends GenericDao<Book> {
    boolean hasUniqueName(Book book) throws DaoException;

    List<Book> getBooksInfo() throws DaoException;

    List<Book> findByPublisher(Integer publisher) throws DaoException;

    List<Book> findByCategory(Integer category);
}
