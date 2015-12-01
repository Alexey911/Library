package com.zhytnik.library.service;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class UserService extends Service<User> {
    private ShaPasswordEncoder passwordEncoder;

    public UserService() {
        passwordEncoder = new ShaPasswordEncoder(256);
    }

    public User create() {
        return new User();
    }

    public void activate(String username) {
        getUserDao().activate(username);
    }

    public void disable(String username) {
        getUserDao().disable(username);
    }

    @Override
    protected boolean isUnique(User user) {
        return getUserDao().hasUniqueLogin(user);
    }

    private UserDao getUserDao() {
        return (UserDao) getDao();
    }

    public void add(User user) {
        UserDao dao = getUserDao();
        if (dao.hasUniqueLogin(user)) {
            encodePassword(user);
            user.setEnabled(true);
            dao.persist(user);
        } else {
            throw new NotUniqueException();
        }
    }

    private void encodePassword(User user) {
        String hashed = passwordEncoder.encodePassword(user.getPassword(), null);
        user.setPassword(hashed);
    }
}
