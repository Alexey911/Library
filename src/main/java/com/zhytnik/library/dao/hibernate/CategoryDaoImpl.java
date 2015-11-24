package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.model.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import static java.util.Objects.isNull;

public class CategoryDaoImpl extends AbstractHibernateDao<Category> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public boolean isUniqueName(String name) throws DaoException {
        Session session = openSession();
        Criteria criteria = session.createCriteria(Category.class);
        Object result = criteria.add(Restrictions.eq("name", name)).uniqueResult();
        closeSession(session);
        return isNull(result);
    }
}