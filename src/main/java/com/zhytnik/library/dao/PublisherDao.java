package com.zhytnik.library.dao;

import com.zhytnik.library.domain.Publisher;

public interface PublisherDao extends GenericDao<Publisher> {
    boolean isUniqueName(Publisher p) throws DaoException;
}
