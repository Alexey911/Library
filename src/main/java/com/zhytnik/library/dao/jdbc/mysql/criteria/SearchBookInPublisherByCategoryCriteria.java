package com.zhytnik.library.dao.jdbc.mysql.criteria;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.jdbc.JDBCCriteria;
import com.zhytnik.library.entity.Book;
import com.zhytnik.library.entity.Category;
import com.zhytnik.library.entity.Publisher;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class SearchBookInPublisherByCategoryCriteria implements JDBCCriteria {
    private Set<Category> categories;
    private Publisher publisher;
    private String name;

    public SearchBookInPublisherByCategoryCriteria() {

    }

    @Override
    public void setParameter(Object param) {
        Book b = (Book) param;
        categories = b.getCategories();
        publisher = b.getPublisher();
        name = b.getName();
    }

    @Override
    public Object getParameter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSearchQuery() {
        StringBuilder query = new StringBuilder(150);
        query.append("SELECT * FROM books WHERE publisher_id = ? AND name = ?");
        if (!categories.isEmpty()) {
            query.append("AND id IN (SELECT id_book FROM book_categories WHERE id_category IN (");
            for (Category category : categories) {
                query.append(category.getId()).append(',');
            }
            query.deleteCharAt(query.length() - 1);
            query.append(") )");
        }
        return query.toString();
    }

    @Override
    public void prepareStatementForSearch(PreparedStatement ps) {
        try {
            ps.setInt(1, publisher.getId());
            ps.setString(2, name);
            int pos = 3;
            for (Category category : categories) {
                ps.setInt(pos++, category.getId());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}