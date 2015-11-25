package com.zhytnik.library.dao;

import com.zhytnik.library.domain.Book;
import com.zhytnik.library.domain.Category;
import com.zhytnik.library.domain.Publisher;

import java.util.Set;

public interface BookDao extends GenericDao<Book> {
    Set<Book> findBooksInPublisherCategories(Publisher publisher, Set<Category> categories);
}
