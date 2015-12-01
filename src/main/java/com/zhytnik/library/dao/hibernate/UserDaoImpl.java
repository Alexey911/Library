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
        return getUser(getCurrentSession(), username);
    }

    private User getUser(Session session, String username) {
        Criteria criteria = session.createCriteria(User.class).
                add(Restrictions.eq("login", username));
        return (User) criteria.uniqueResult();
    }

    @Transactional(readOnly = false)
    @Override
    public void activate(String username) {
        setEnable(username, true);
    }

    @Transactional(readOnly = false)
    @Override
    public void disable(String username) {
        setEnable(username, false);
    }

    private void setEnable(String username, boolean isEnable) {
        Session session = getCurrentSession();
        User user = getUser(session, username);
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
