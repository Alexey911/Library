package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

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
        Session session = getCurrentSession();
        User user = getUser(session, username);
        user.setEnabled(true);
        session.save(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void disable(String username) {
        Session session = getCurrentSession();
        User user = getUser(session, username);
        user.setEnabled(false);
        session.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUniqueLogin(User u) {
        String login = u.getLogin();
        Criteria criteria = getLazyCriteria();
        User daoUser = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        return isNull(daoUser) || (u.isStored() && u.getId().equals(daoUser.getId()));
    }

    private Criteria getLazyCriteria() {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.setProjection(Projections.projectionList().
                add(Projections.property("id"), "id").
                add(Projections.property("login"), "login")).
                setResultTransformer(Transformers.aliasToBean(User.class));
        return criteria;
    }
}
