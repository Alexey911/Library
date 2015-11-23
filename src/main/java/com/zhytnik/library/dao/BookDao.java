package com.zhytnik.library.dao;

import com.zhytnik.library.model.Book;
import com.zhytnik.library.model.Category;
import com.zhytnik.library.model.Publisher;

import java.util.Set;

public interface BookDao extends GenericDao<Book> {
    Set<Book> findBooksInPublisherCategories(Publisher publisher, Set<Category> categories);
}
