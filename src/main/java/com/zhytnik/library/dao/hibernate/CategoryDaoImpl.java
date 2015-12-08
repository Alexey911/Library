package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.domain.Category;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

public class CategoryDaoImpl extends AbstractHibernateDao<Category> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean hasUniqueName(Category category) throws DaoException {
        Criteria criteria = getLazyCriteria(Projections.projectionList().
                add(Projections.property("id").as("id")).
                add(Projections.property("name").as("name")));
        criteria.add(Restrictions.eq("name", category.getName()));
        return isUnique(criteria, category);
    }
}
