package com.zhytnik.library.dao;

import com.zhytnik.library.domain.DomainObject;

import java.util.List;

public interface GenericDao<T extends DomainObject> {
    void persist(T object) throws DaoException;

    T findById(Integer id) throws DaoException;

    void delete(Integer id) throws DaoException;

    void update(T object) throws DaoException;

    List<T> getAll() throws DaoException;
}
