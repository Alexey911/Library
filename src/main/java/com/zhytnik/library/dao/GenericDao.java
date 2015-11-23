package com.zhytnik.library.dao;

import com.zhytnik.library.model.DomainObject;

import java.util.Set;

public interface GenericDao<T extends DomainObject> {
    T persist(T object) throws DaoException;

    T findById(Integer id) throws DaoException;

    void update(T object) throws DaoException;

    void delete(T object) throws DaoException;

    Set<T> getAll() throws DaoException;
}