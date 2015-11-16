package com.zhytnik.library.dao.jdbc;

import com.zhytnik.library.dao.DaoException;

import java.sql.PreparedStatement;

public interface JDBCCriteria {
    String getSearchQuery();

    void prepareStatementForSearch(PreparedStatement ps) throws DaoException;

    void addParameter(Object param);
}