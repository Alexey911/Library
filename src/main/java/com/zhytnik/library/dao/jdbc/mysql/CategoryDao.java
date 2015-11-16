package com.zhytnik.library.dao.jdbc.mysql;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.jdbc.AbstractJDBCDao;
import com.zhytnik.library.dao.jdbc.JDBCCriteria;
import com.zhytnik.library.dao.jdbc.mysql.criteria.SearchCategoryByNameCriteria;
import com.zhytnik.library.entity.Category;
import org.apache.log4j.Level;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CategoryDao extends AbstractJDBCDao<Category> {
    private JDBCCriteria criteria;

    public CategoryDao() {
        super();
        criteria = new SearchCategoryByNameCriteria();
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM categories";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO categories (name, description) VALUES (?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE categories SET name = ?, description = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM categories WHERE id = ?";
    }

    @Override
    protected Set<Category> parseResultSet(ResultSet rs) throws DaoException {
        Set<Category> set = new HashSet<>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));
                    set.add(category);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
                throw new DaoException(e);
            }
        }
        return set;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement st, Category category) throws DaoException {
        try {
            st.setString(1, category.getName());
            st.setString(2, category.getDescription());
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement st, Category category) throws DaoException {
        try {
            st.setString(1, category.getName());
            st.setString(2, category.getDescription());
            st.setInt(3, category.getId());
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    public Category create() throws DaoException {
        Category c = new Category();
        return persist(c);
    }

    @Override
    public Set<Category> find(Object param) {
        criteria.addParameter(param);
        return super.find(criteria);
    }
}