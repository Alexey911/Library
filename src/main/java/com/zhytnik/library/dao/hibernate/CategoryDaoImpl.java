package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.domain.Category;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

public class CategoryDaoImpl extends AbstractHibernateDao<Category> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUniqueName(String name) throws DaoException {
        Criteria criteria = getCurrentSession().createCriteria(Category.class);
        Object result = criteria.add(Restrictions.eq("name", name)).uniqueResult();
        return isNull(result);
    }
}
