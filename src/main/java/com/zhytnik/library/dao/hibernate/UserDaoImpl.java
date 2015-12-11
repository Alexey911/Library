package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Boolean.FALSE;

public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByUserName(String username) {
        Criteria criteria = getCurrentSession().createCriteria(User.class).
                add(Restrictions.eq("login", username));
        return (User) criteria.uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean hasUniqueLogin(User user) {
        Criteria criteria = getLazyCriteria(Projections.projectionList().
                add(Projections.property("id"), "id").
                add(Projections.property("login"), "login"));
        criteria.add(Restrictions.eq("login", user.getLogin()));
        return isUnique(criteria, user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUnconfirmedUsers() {
        Criteria criteria = getCurrentSession().createCriteria(User.class).
                add(Restrictions.eq("confirmed", FALSE));
        return criteria.list();
    }
}
