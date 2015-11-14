package com.zhytnik.library.dao.jdbc;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.DaoFactory;
import com.zhytnik.library.entity.DomainObject;

import java.sql.Connection;

public abstract class Dependence<T extends DomainObject> {
    private DaoFactory daoFactory;

    public Dependence(DaoFactory factory) {
        daoFactory = factory;
    }

    protected DaoFactory getDaoFactory() {
        return daoFactory;
    }

    protected abstract void update(T object, Connection connection) throws DaoException;

    protected abstract void delete(T object, Connection connection) throws DaoException;
}