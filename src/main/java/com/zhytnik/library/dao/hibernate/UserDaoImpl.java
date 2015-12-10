package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

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

    @Transactional
    @Override
    public void activate(Integer id) {
        setEnable(id, true);
    }

    @Transactional
    @Override
    public void disable(Integer id) {
        setEnable(id, false);
    }

    private void setEnable(Integer id, boolean isEnable) {
        Session session = getCurrentSession();
        User user = (User) session.load(User.class, id);
        user.setEnabled(isEnable);
        session.merge(user);
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
    public Set<User> getNotConfirmedUsers() {
        Criteria criteria = getCurrentSession().createCriteria(User.class).
                add(Restrictions.eq("confirmed", FALSE));
        return new HashSet<>(criteria.list());
    }

    @Transactional
    public void confirm(List<Integer> users) {
        Session session = getCurrentSession();
        for (Integer id : users) {
            User user = (User) session.load(User.class, id);
            user.setConfirmed(TRUE);
            session.merge(user);
        }
        session.flush();
    }
}
