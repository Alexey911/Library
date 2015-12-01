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
    public boolean hasUniqueName(Publisher p) throws DaoException {
        Criteria criteria = getLazyCriteria(Projections.projectionList().
                add(Projections.property("id"), "id").
                add(Projections.property("name"), "name"));
        criteria.add(Restrictions.eq("name", p.getName()));
        return isUnique(criteria, p);
    }
}
