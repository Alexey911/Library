package com.zhytnik.library.dao.jdbc.mysql.criteria;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.jdbc.JDBCCriteria;
import com.zhytnik.library.model.Category;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchCategoryByNameCriteria implements JDBCCriteria {
    private String name;

    public SearchCategoryByNameCriteria() {
    }

    @Override
    public void addParameter(Object param) {
        Category c = (Category) param;
        name = c.getName();
    }

    @Override
    public String getSearchQuery() {
        return "SELECT * FROM categories WHERE name = ?";
    }

    @Override
    public void prepareStatementForSearch(PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, name);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}