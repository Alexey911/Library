package com.zhytnik.library.dao.jdbc.mysql.criteria;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.jdbc.JDBCCriteria;
import com.zhytnik.library.entity.Publisher;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchPublisherByNameCriteria implements JDBCCriteria {
    private String name;

    public SearchPublisherByNameCriteria() {
    }

    @Override
    public void setParameter(Object param) {
        Publisher p = (Publisher) param;
        name = p.getName();
    }

    @Override
    public Object getParameter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSearchQuery() {
        return "SELECT * FROM publishers WHERE name = ?";
    }

    @Override
    public void prepareStatementForSearch(PreparedStatement ps) {
        try {
            ps.setString(1, name);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}