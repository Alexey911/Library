package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.PublisherDao;
import com.zhytnik.library.model.Category;
import com.zhytnik.library.model.Publisher;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PublisherDaoImpl extends AbstractHibernateDao<Publisher> implements PublisherDao {
    public PublisherDaoImpl() {
        super(Publisher.class);
    }

    @Override
    public Set<Publisher> findByName(String name) throws DaoException {
        Session session = openSession();
        Criteria criteria = session.createCriteria(Category.class);
        List<Publisher> publishers = criteria.add(Restrictions.eq("name", name)).list();
        closeSession(session);
        return new HashSet<>(publishers);
    }
}
