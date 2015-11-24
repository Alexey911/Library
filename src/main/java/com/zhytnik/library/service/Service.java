package com.zhytnik.library.service;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.model.DomainObject;
import com.zhytnik.library.service.exception.DeleteAssociatedObjectException;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Set;

public abstract class Service<T extends DomainObject> {
    protected Logger logger;

    private GenericDao<T> dao;

    public Service() {
        logger = Logger.getLogger(getClass());
    }

    public abstract T create();

    public T findById(Integer id) {
        return dao.findById(id);
    }

    public void add(T object) {
        validateUnique(object);
        logger.log(Level.INFO, "Add " + object);
        dao.persist(object);
    }

    public void update(T object) {
        validateUnique(object);
        logger.log(Level.INFO, "Update " + object);
        dao.update(object);
    }

    public void delete(T object) {
        logger.log(Level.INFO, "Delete " + object);
        try {
            dao.delete(object.getId());
        } catch (DaoException e) {
            logger.log(Level.WARN, e);
            throw new DeleteAssociatedObjectException(e.getMessage());
        }
    }

    public Set<T> getAll() {
        logger.log(Level.INFO, "Get All objects");
        return dao.getAll();
    }

    private void validateUnique(T object) throws NotUniqueException {
        if (!isUnique(object)) {
            String msg = "Not unique " + object;
            logger.log(Level.WARN, msg);
            throw new NotUniqueException();
        }
    }

    public abstract boolean isUnique(T object);

    protected GenericDao<T> getDao() {
        return dao;
    }

    public void setDao(GenericDao<T> dao) {
        this.dao = dao;
    }
}