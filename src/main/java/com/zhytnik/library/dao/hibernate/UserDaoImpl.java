package com.zhytnik.library.dao.hibernate;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional(readOnly = true)
    @Override
    public User findByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class).
                add(Restrictions.eq("login", username));
        User user = (User) criteria.uniqueResult();
        user.setEnabled(true);
        return user;
    }

    @Override
    public void activate(String username) {
    }

    @Override
    public void disable(String username) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
