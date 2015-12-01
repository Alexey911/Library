package com.zhytnik.library.dao.jdbc.mysql;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.PublisherDao;
import com.zhytnik.library.dao.jdbc.AbstractJDBCDao;
import com.zhytnik.library.domain.Publisher;
import org.apache.log4j.Level;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PublisherDaoImpl extends AbstractJDBCDao<Publisher> implements PublisherDao {
    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM publishers";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO publishers (name, address) VALUES (?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE publishers SET name = ?, address = ? WHERE id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM publishers WHERE id = ?";
    }

    @Override
    protected Set<Publisher> parseResultSet(ResultSet rs) throws DaoException {
        Set<Publisher> set = new HashSet<>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    Publisher publisher = new Publisher();
                    publisher.setId(rs.getInt("id"));
                    publisher.setName(rs.getString("name"));
                    publisher.setAddress(rs.getString("address"));
                    set.add(publisher);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
                throw new DaoException(e);
            }
        }
        return set;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement st, Publisher publisher) throws DaoException {
        try {
            st.setString(1, publisher.getName());
            st.setString(2, publisher.getAddress());
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement st, Publisher publisher) throws DaoException {
        try {
            st.setString(1, publisher.getName());
            st.setString(2, publisher.getAddress());
            st.setInt(3, publisher.getId());
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean hasUniqueName(Publisher p) throws DaoException {
        return false;
    }

    /*@Override
    public Set<Publisher> find(Object param) {
        criteria.addParameter(param);
        return super.find(criteria);
    }*/
}