package com.zhytnik.library.service;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import com.zhytnik.library.service.exception.NotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class UserService extends Service<User> {
    @Autowired
    private MessageDigestPasswordEncoder passwordEncoder;

    public void setPasswordEncoder(MessageDigestPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create() {
        return new User();
    }

    public void activate(Integer id) {
        getUserDao().activate(id);
    }

    public void disable(Integer id) {
        getUserDao().disable(id);
    }

    @Override
    protected boolean isUnique(User user) {
        return getUserDao().hasUniqueLogin(user);
    }

    @Override
    public void add(User user) throws NotUniqueException {
        execute(user, () -> getDao().persist(user));
    }

    @Override
    public void update(User user) throws NotUniqueException {
        execute(user, () -> getDao().update(user));
    }

    private void execute(User user, Runnable action) throws NotUniqueException {
        UserDao dao = getUserDao();
        if (dao.hasUniqueLogin(user)) {
            encodePassword(user);
            user.setEnabled(true);
            action.run();
        } else {
            throw new NotUniqueException();
        }
    }

    private UserDao getUserDao() {
        return (UserDao) getDao();
    }

    private void encodePassword(User user) {
        String hashed = passwordEncoder.encodePassword(user.getPassword(), null);
        user.setPassword(hashed);
    }

    public User findByName(String username) {
        return getUserDao().findByUserName(username);
    }
}
