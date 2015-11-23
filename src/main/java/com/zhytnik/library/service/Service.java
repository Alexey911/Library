package com.zhytnik.library.service;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.SearchDao;
import com.zhytnik.library.model.DomainObject;
import com.zhytnik.library.service.exception.DeleteAssociatedObjectException;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Set;

public abstract class Service<T extends DomainObject> {
    protected Logger logger;

    private SearchDao<T> dao;

    public Service() {
        logger = Logger.getLogger(getClass());
    }

    public void setDao(SearchDao<T> dao) {
        this.dao = dao;
    }

    public T findById(Integer id) {
        return dao.findById(id);
    }

    public void add(T object) {
        validateFullness(object);
        validateUnique(object);
        logger.log(Level.INFO, "Add " + object);
        dao.persist(object);
    }

    public void update(T object) {
        validateFullness(object);
        validateUnique(object);
        logger.log(Level.INFO, "Update " + object);
        dao.update(object);
    }

    public void delete(T object) {
        logger.log(Level.INFO, "Delete " + object);
        try {
            dao.delete(object);
        } catch (DaoException e) {
            logger.log(Level.WARN, e);
            throw new DeleteAssociatedObjectException(e.getMessage());
        }
    }

    public Set<T> getAll() {
        logger.log(Level.INFO, "Get All objects");
        return dao.getAll();
    }

    private void validateUnique(T object) {
        Set<T> items = dao.find(object);
        if (!isUniqueInSet(items, object)) {
            String msg = "Not unique " + object;
            logger.log(Level.WARN, msg);
            throw new NotUniqueException(msg, getExceptionDescription(object));
        }
    }

    private boolean isUniqueInSet(Set<T> items, T item) {
        if (items.size() > 1) {
            return false;
        }
        if (items.isEmpty()) {
            return true;
        }
        T daoItem = items.iterator().next();
        return daoItem.getId().equals(item.getId());
    }

    protected void validateFullness(T object) {

    }

    protected void throwIllegalArgException(String message) {
        logger.log(Level.WARN, message);
        throw new IllegalArgumentException(message);
    }

    protected String getExceptionDescription(T object) {
        return object.toString();
    }
}