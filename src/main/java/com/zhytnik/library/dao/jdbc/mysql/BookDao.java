package com.zhytnik.library.dao.jdbc.mysql;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.jdbc.AbstractJDBCDao;
import com.zhytnik.library.dao.jdbc.JDBCCriteria;
import com.zhytnik.library.dao.jdbc.mysql.criteria.SearchBookInPublisherByCategoryCriteria;
import com.zhytnik.library.model.Book;
import com.zhytnik.library.model.Publisher;
import org.apache.log4j.Level;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class BookDao extends AbstractJDBCDao<Book> {
    private JDBCCriteria criteria;

    public BookDao() {
        super();
        criteria = new SearchBookInPublisherByCategoryCriteria();
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM books";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO books (name, page_count, authors, annotation, " +
                "publisher_id, publishing_year, weight) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE books SET name = ?, page_count = ?, authors = ?, annotation = ?," +
                "publisher_id = ?, publishing_year = ?, weight = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM books WHERE id = ?";
    }

    @Override
    protected Set<Book> parseResultSet(ResultSet rs) throws DaoException {
        Set<Book> set = new HashSet<>();
        if (rs != null)
            try {
                while (rs.next()) {
                    Book book = new Book();
                    book.setName(rs.getString("name"));
                    book.setPageCount(rs.getInt("page_count"));
                    book.setAuthors(rs.getString("authors"));
                    book.setAnnotation(rs.getString("annotation"));
                    Publisher p = new Publisher();
                    int id = rs.getInt("publisher_id");
                    if (id != -1) {
                        p.setId(id);
                    }
                    book.setPublisher(p);
                    book.setPublishingYear(rs.getInt("publishing_year"));
                    book.setWeight(rs.getInt("weight"));
                    book.setId(rs.getInt("id"));
                    set.add(book);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        return set;
    }

    private void setFields(PreparedStatement st, Book book) throws SQLException {
        st.setString(1, book.getName());
        st.setInt(2, book.getPageCount());
        st.setString(3, book.getAuthors());
        st.setString(4, book.getAnnotation());
        if (book.getPublisher() != null) {
            st.setInt(5, book.getPublisher().getId());
        } else {
            st.setInt(5, -1);
        }
        st.setInt(6, book.getPublishingYear());
        st.setInt(7, book.getWeight());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement st, Book book) throws DaoException {
        try {
            setFields(st, book);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement st, Book book) throws DaoException {
        try {
            setFields(st, book);
            st.setInt(8, book.getId());
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    public Book create() throws DaoException {
        Book b = new Book();
        return persist(b);
    }

    @Override
    public Set<Book> find(Object param) {
        criteria.addParameter(param);
        return super.find(criteria);
    }
}