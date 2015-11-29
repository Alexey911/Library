package com.zhytnik.library.dao;

import com.zhytnik.library.domain.Book;

import java.util.Set;

public interface BookDao extends GenericDao<Book> {
    Set<Book> findBooksInPublisherCategories(Integer publisher, Set<Integer> categories);

    Set<Book> findBooksByCategories(Set<Integer> categories);

    boolean isUniqueName(String name);
}
