package com.zhytnik.library.service;

import com.zhytnik.library.dao.DaoFactory;
import com.zhytnik.library.dao.jdbc.mysql.MySQLDaoFactory;
import com.zhytnik.library.entity.Book;
import com.zhytnik.library.entity.Category;
import com.zhytnik.library.entity.Publisher;

public class ServiceFactory {
    private DaoFactory daofactory;

    private static ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
        daofactory = new MySQLDaoFactory();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public BookService getBookService() {
        BookService service = new BookService();
        service.setDao(daofactory.getDao(Book.class));
        return service;
    }

    public CategoryService getCategoryService() {
        CategoryService service = new CategoryService();
        service.setDao(daofactory.getDao(Category.class));
        return service;
    }

    public PublisherService getPublisherService() {
        PublisherService service = new PublisherService();
        service.setDao(daofactory.getDao(Publisher.class));
        return service;
    }
}