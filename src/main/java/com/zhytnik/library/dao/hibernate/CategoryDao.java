package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.model.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CategoryDao extends AbstractHibernateDao<Category>
        implements com.zhytnik.library.dao.CategoryDao {
    public CategoryDao() {
        super(Category.class);
    }

    @Override
    public Category findByName(String name) throws DaoException {
        Session session = openSession();
        Criteria criteria = session.createCriteria(Category.class);
        Object category = criteria.add(Restrictions.eq("name", name)).uniqueResult();
        closeSession(session);
        return (Category) category;
    }
}