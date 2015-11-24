package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.model.DomainObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractHibernateDao<T extends DomainObject> implements GenericDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> aClass;

    public AbstractHibernateDao(Class<T> aClass) {
        this.aClass = aClass;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return session;
    }

    @Transactional
    @Override
    public T persist(T object) throws DaoException {
        getCurrentSession().save(object);
        return object;
    }

    @Transactional(readOnly = true)
    @Override
    public T findById(Integer id) throws DaoException {
        Object item = getCurrentSession().get(aClass, id);
        @SuppressWarnings("unchecked")
        T result = (T) item;
        return result;
    }

    @Transactional
    @Override
    public void update(T object) throws DaoException {
        getCurrentSession().update(object);
    }

    @Transactional
    @Override
    public void delete(T object) throws DaoException {
        Integer id = object.getId();
        Session session = getCurrentSession();
        Object item = session.load(aClass, id);
        session.delete(item);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<T> getAll() throws DaoException {
        List<T> items = getCurrentSession().createCriteria(aClass).list();
        return new HashSet<>(items);
    }
}