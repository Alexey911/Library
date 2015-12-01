package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = false)
    @Override
    public void activate(Integer id) {
        setEnable(id, true);
    }

    @Transactional(readOnly = false)
    @Override
    public void disable(Integer id) {
        setEnable(id, false);
    }

    private void setEnable(Integer id, boolean isEnable) {
        Session session = getCurrentSession();
        User user = findById(id);
        user.setEnabled(isEnable);
        session.save(user);
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
}
