package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.model.DomainObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    protected Session openSession() {
        return sessionFactory.openSession();
    }

    protected Session openSessionWithTransaction() throws DaoException {
        Session session;
        try {
            session = sessionFactory.openSession();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        session.beginTransaction();
        return session;
    }

    protected void closeSession(Session session) throws DaoException {
        try {
            session.close();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    protected void closeSessionWithTransaction(Session session) throws DaoException {
        session.getTransaction().commit();
        closeSession(session);
    }

    @Override
    public T persist(T object) throws DaoException {
        Session session = openSessionWithTransaction();
        session.save(object);
        closeSessionWithTransaction(session);
        return object;
    }

    @Override
    public T findById(Integer id) throws DaoException {
        Session session = openSession();
        Object item = session.get(aClass, id);
        closeSession(session);
        @SuppressWarnings("unchecked")
        T result = (T) item;
        return result;
    }

    @Override
    public void update(T object) throws DaoException {
        Session session = openSessionWithTransaction();
        session.update(object);
        closeSessionWithTransaction(session);
    }

    @Override
    public void delete(T object) throws DaoException {
        Integer id = object.getId();
        Session session = openSessionWithTransaction();
        Object item = session.load(aClass, id);
        session.delete(item);
        closeSessionWithTransaction(session);
    }

    @Override
    public Set<T> getAll() throws DaoException {
        Session session = openSession();
        List<T> items = session.createCriteria(aClass).list();
        closeSession(session);
        return new HashSet<>(items);
    }
}