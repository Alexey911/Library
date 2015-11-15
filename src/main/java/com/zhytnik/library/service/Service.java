package com.zhytnik.library.service;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.dao.searchdao.Criteria;
import com.zhytnik.library.dao.searchdao.SearchDao;
import com.zhytnik.library.dao.searchdao.SimpleCriteria;
import com.zhytnik.library.entity.DomainObject;
import com.zhytnik.library.service.exception.DeleteAssociatedObjectException;
import com.zhytnik.library.service.exception.NotUniqueNameException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Set;

public abstract class Service<T extends DomainObject> {
    protected static Logger logger;
    private GenericDao<T> dao;
    private Criteria criteria;

    public Service() {
        logger = Logger.getLogger(getClass());
        criteria = new SimpleCriteria();
    }

    public void setDao(GenericDao<T> dao) {
        this.dao = dao;
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
        criteria.setParameter(object);
        Set<T> items = ((SearchDao) dao).findByCriteria(criteria);
        if (!isUniqueInSet(items, object)) {
            String msg = "Not unique " + object;
            logger.log(Level.WARN, msg);
            throw new NotUniqueNameException(msg + "!");
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

    protected abstract void validateFullness(T object);

    protected void throwIllegalArgException(String message) {
        logger.log(Level.WARN, message);
        throw new IllegalArgumentException(message);
    }
}