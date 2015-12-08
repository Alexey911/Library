package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.PublisherDao;
import com.zhytnik.library.domain.Publisher;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

public class PublisherDaoImpl extends AbstractHibernateDao<Publisher> implements PublisherDao {
    public PublisherDaoImpl() {
        super(Publisher.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean hasUniqueName(Publisher publisher) throws DaoException {
        Criteria criteria = getLazyCriteria(Projections.projectionList().
                add(Projections.property("id").as("id")).
                add(Projections.property("name").as("name")));
        criteria.add(Restrictions.eq("name", publisher.getName()));
        return isUnique(criteria, publisher);
    }
}
