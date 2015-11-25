package com.zhytnik.library.dao.jdbc;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.domain.DomainObject;

import java.sql.Connection;

public abstract class Dependence<T extends DomainObject> {
    protected abstract void update(T object, Connection connection) throws DaoException;

    protected abstract void delete(T object, Connection connection) throws DaoException;
}