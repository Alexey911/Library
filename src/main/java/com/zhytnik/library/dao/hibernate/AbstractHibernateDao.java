package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.GenericDao;
import com.zhytnik.library.domain.DomainObject;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

public abstract class AbstractHibernateDao<T extends DomainObject> implements GenericDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public AbstractHibernateDao(Class<T> clazz) {
        this.clazz = clazz;
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
    public void persist(T object) throws DaoException {
        if (object.isStored()) {
            throw new DaoException();
        }
        getCurrentSession().save(object);
    }

    @Transactional(readOnly = true)
    @Override
    public T findById(Integer id) throws DaoException {
        Object item = getCurrentSession().get(clazz, id);
        @SuppressWarnings("unchecked")
        T result = (T) item;
        return result;
    }

    @Transactional
    @Override
    public void update(T object) throws DaoException {
        if (!object.isStored()) {
            throw new DaoException();
        }
        getCurrentSession().update(object);
    }

    @Transactional
    @Override
    public void delete(Integer id) throws DaoException {
        Session session = getCurrentSession();
        Object item = session.load(clazz, id);
        session.delete(item);
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> getAll() throws DaoException {
        return getCurrentSession().createCriteria(clazz).list();
    }

    protected Criteria getLazyCriteria(Projection projection) {
        Criteria criteria = getCurrentSession().createCriteria(clazz).setProjection(projection);
        criteria.setResultTransformer(Transformers.aliasToBean(clazz));
        return criteria;
    }

    protected boolean isUnique(Criteria criteria, T object) {
        Object result = criteria.uniqueResult();
        @SuppressWarnings("unchecked")
        T daoItem = (T) result;
        return isNull(daoItem) || (object.isStored() && object.getId().equals(daoItem.getId()));
    }
}
