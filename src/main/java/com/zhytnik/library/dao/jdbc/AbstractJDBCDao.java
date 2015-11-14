package com.zhytnik.library.dao.jdbc;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.dao.searchdao.Criteria;
import com.zhytnik.library.dao.searchdao.SearchDao;
import com.zhytnik.library.entity.DomainObject;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

public abstract class AbstractJDBCDao<T extends DomainObject> implements GenericDao<T>, SearchDao<T> {
    protected Logger logger;
    private DataSource dataSource;
    private Set<Dependence<? super T>> dependencies;

    public AbstractJDBCDao() {
        dependencies = new HashSet<>();
        logger = Logger.getLogger(getClass());
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addDependence(Dependence<? super T> dependence) {
        dependencies.add(dependence);
    }

    private void updateDependence(T object, Connection connection) {
        dependencies.stream().forEach(dependence -> dependence.update(object, connection));
    }

    protected abstract String getSelectQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract Set<T> parseResultSet(ResultSet rs) throws DaoException;

    protected abstract void prepareStatementForInsert(PreparedStatement st, T object) throws DaoException;

    protected abstract void prepareStatementForUpdate(PreparedStatement st, T object) throws DaoException;

    @Override
    public T findById(Integer id) throws DaoException {
        if (id == null) {
            throw new DaoException("Unknown id = null!");
        }
        T item;
        Set<T> items;
        String query = getSelectQuery() + " WHERE id = " + id;
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet set = statement.executeQuery(query);
            items = parseResultSet(set);
            if (items == null || items.isEmpty()) {
                String msg = "Item with ID = " + id + " not found!";
                logger.log(Level.WARN, msg);
                throw new DaoException(msg);
            }
            item = items.iterator().next();
            updateDependence(item, con);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return item;
    }

    @Override
    public Set<T> getAll() throws DaoException {
        Set<T> items;
        String query = getSelectQuery();
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet set = statement.executeQuery(query);
            items = parseResultSet(set);
            items.forEach(item -> updateDependence(item, con));
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return items;
    }

    @Override
    public void update(T object) throws DaoException {
        validateId(object);
        try (Connection con = dataSource.getConnection()) {
            updateDependence(object, con);
            String query = getUpdateQuery();
            try (PreparedStatement statement = con.prepareStatement(query)) {
                prepareStatementForUpdate(statement, object);
                statement.executeUpdate();
                commit(con);
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
                rollback(con);
                throw new DaoException(e);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        validateId(object);
        try (Connection con = dataSource.getConnection()) {
            String query = getDeleteQuery();
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setObject(1, object.getId());
                int recordCount = statement.executeUpdate();
                if (recordCount == 0) {
                    String msg = "Object not found: " + object;
                    logger.log(Level.WARN, msg);
                    throw new DaoException(msg);
                }
                dependencies.stream().forEach(dependence -> dependence.delete(object, con));
                commit(con);
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
                rollback(con);
                throw new DaoException(e);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        object.setId(null);
    }

    @Override
    public T persist(T object) throws DaoException {
        if (object.getId() != null) {
            String msg = object + " is already persist!";
            logger.log(Level.WARN, msg);
            throw new DaoException(msg);
        }
        try (Connection con = dataSource.getConnection()) {
            String query = getCreateQuery();
            try (PreparedStatement statement =
                         con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                prepareStatementForInsert(statement, object);
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                    object.setId(id);
                }
                updateDependence(object, con);
                commit(con);
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
                rollback(con);
                throw new DaoException(e);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return object;
    }

    @Override
    public Set<T> findByCriteria(Criteria c) {
        if (c == null) {
            return getAll();
        }
        if (!(c instanceof JDBCCriteria)) {
            throw new DaoException("Unknown criteria: " + c);
        }
        JDBCCriteria criteria = (JDBCCriteria) c;
        Set<T> items;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(criteria.getSearchQuery())) {
            criteria.prepareStatementForSearch(statement);
            ResultSet set = statement.executeQuery();
            items = parseResultSet(set);
            for (T item : items) {
                updateDependence(item, con);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return items;
    }

    private void commit(Connection c) throws SQLException {
        try {
            c.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw e;
        }
    }

    private void rollback(Connection c) throws SQLException {
        try {
            c.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw e;
        }
    }

    private void validateId(DomainObject object) {
        if (isNull(object.getId())) {
            String msg = "Unknown object: " + object;
            logger.log(Level.WARN, msg);
            throw new DaoException(msg);
        }
    }
}