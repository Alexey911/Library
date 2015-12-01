package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.domain.Category;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

public class CategoryDaoImpl extends AbstractHibernateDao<Category> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUniqueName(Category c) throws DaoException {
        String name = c.getName();
        Criteria criteria = getLazyCriteria();
        Category daoCategory = (Category) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        return isNull(daoCategory) || (c.isStored() && c.getId().equals(daoCategory.getId()));
    }

    private Criteria getLazyCriteria() {
        Criteria criteria = getCurrentSession().createCriteria(Category.class);
        criteria.setProjection(Projections.projectionList().
                add(Projections.property("id"), "id").
                add(Projections.property("name"), "name")).
                setResultTransformer(Transformers.aliasToBean(Category.class));
        return criteria;
    }
}
