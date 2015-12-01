package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.PublisherDao;
import com.zhytnik.library.domain.Publisher;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

public class PublisherDaoImpl extends AbstractHibernateDao<Publisher> implements PublisherDao {
    public PublisherDaoImpl() {
        super(Publisher.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUniqueName(Publisher p) throws DaoException {
        Publisher daoPublisher = (Publisher) getSearchCriteria().
                add(Restrictions.eq("name", p.getName())).uniqueResult();
        return isNull(daoPublisher) || (p.isStored() && p.getId().equals(daoPublisher.getId()));
    }

    private Criteria getSearchCriteria() {
        return getLazyCriteria(Projections.projectionList().
                add(Projections.property("id"), "id").
                add(Projections.property("name"), "name"));
    }
}
