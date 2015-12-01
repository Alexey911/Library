package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.DaoException;
import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.Category;
import com.zhytnik.library.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = true)
    @Override
    public User findByUserName(String username) {
        return getUser(getCurrentSession(), username);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Integer id) {
        return (User) getCurrentSession().get(User.class, id);
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

    @Transactional(readOnly = false)
    @Override
    public void update(User user) {
        if (isNull(user.getId())) {
            throw new DaoException();
        }
        getCurrentSession().update(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(User user) {
        getCurrentSession().delete(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void add(User user) {
        if (!isNull(user.getId())) {
            throw new DaoException();
        }
        getCurrentSession().save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return getCurrentSession().createCriteria(User.class).list();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUniqueLogin(User u) {
        String login = u.getLogin();
        Criteria criteria = getLazyCriteria();
        Category daoCategory = (Category) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        return isNull(daoCategory) || (u.isStored() && u.getId().equals(daoCategory.getId()));
    }

    private Criteria getLazyCriteria() {
        Criteria criteria = getCurrentSession().createCriteria(Category.class);
        criteria.setProjection(Projections.projectionList().
                add(Projections.property("id"), "id").
                add(Projections.property("login"), "login")).
                setResultTransformer(Transformers.aliasToBean(Category.class));
        return criteria;
    }
}
