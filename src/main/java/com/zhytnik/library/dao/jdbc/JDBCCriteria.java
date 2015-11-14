package com.zhytnik.library.dao.jdbc;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.searchdao.Criteria;

import java.sql.PreparedStatement;

public interface JDBCCriteria extends Criteria {
    String getSearchQuery();

    void prepareStatementForSearch(PreparedStatement ps) throws DaoException;
}