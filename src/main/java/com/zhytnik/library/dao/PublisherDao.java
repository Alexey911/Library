package com.zhytnik.library.dao;

import com.zhytnik.library.model.Publisher;

public interface PublisherDao extends GenericDao<Publisher> {
    boolean isUniqueName(String name) throws DaoException;
}
