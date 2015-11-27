package com.zhytnik.library.service;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.domain.DomainObject;
import com.zhytnik.library.service.exception.DeleteAssociatedObjectException;
import com.zhytnik.library.service.exception.NotFoundItemException;
import com.zhytnik.library.service.exception.NotUniqueException;

import java.util.Set;

import static java.util.Objects.isNull;

public abstract class Service<T extends DomainObject> {
    private GenericDao<T> dao;

    public abstract T create();

    public T findById(Integer id) {
        T item = dao.findById(id);
        if (isNull(item)) {
            throw new NotFoundItemException();
        }
        return item;
    }

    public void add(T object) {
        validateUnique(object);
        dao.persist(object);
    }

    public void update(T object) {
        validateUnique(object);
        dao.update(object);
    }

    public void delete(T object) {
        try {
            dao.delete(object.getId());
        } catch (DaoException e) {
            throw new DeleteAssociatedObjectException(e.getMessage());
        }
    }

    public Set<T> getAll() {
        return dao.getAll();
    }

    private void validateUnique(T object) throws NotUniqueException {
        if (!isUnique(object)) {
            throw new NotUniqueException();
        }
    }

    public abstract boolean isUnique(T object);

    protected GenericDao<T> getDao() {
        return dao;
    }

    protected void setDao(GenericDao<T> dao) {
        this.dao = dao;
    }
}