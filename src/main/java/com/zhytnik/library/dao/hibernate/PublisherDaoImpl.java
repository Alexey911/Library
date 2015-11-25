package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.PublisherDao;
import com.zhytnik.library.domain.Category;
import com.zhytnik.library.domain.Publisher;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

public class PublisherDaoImpl extends AbstractHibernateDao<Publisher> implements PublisherDao {
    public PublisherDaoImpl() {
        super(Publisher.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUniqueName(String name) throws DaoException {
        Criteria criteria = getCurrentSession().createCriteria(Category.class);
        Object result = criteria.add(Restrictions.eq("name", name)).uniqueResult();
        return isNull(result);
    }
}
