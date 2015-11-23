package com.zhytnik.library.dao;

import com.zhytnik.library.model.Publisher;

import java.util.Set;

public interface PublisherDao extends GenericDao<Publisher> {
    Set<Publisher> findByName(String name) throws DaoException;
}
