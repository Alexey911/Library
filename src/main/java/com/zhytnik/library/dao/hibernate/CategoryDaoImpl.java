package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.model.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryDaoImpl extends AbstractHibernateDao<Category>
        implements com.zhytnik.library.dao.CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public Set<Category> findByName(String name) throws DaoException {
        Session session = openSession();
        Criteria criteria = session.createCriteria(Category.class);
        List<Category> categories = criteria.add(Restrictions.eq("name", name)).list();
        closeSession(session);
        return new HashSet<>(categories);
    }
}