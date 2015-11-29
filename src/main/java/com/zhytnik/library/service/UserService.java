package com.zhytnik.library.service;

import com.zhytnik.library.dao.UserDao;
import com.zhytnik.library.domain.User;
import com.zhytnik.library.security.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static java.util.Objects.isNull;

public class UserService implements UserDetailsService {
    @Autowired
    @Qualifier("userDao")
    private UserDao dao;

    public void setUserDao(UserDao userDao) {
        dao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByUserName(username);
        if (isNull(user)) {
            throw new UsernameNotFoundException("Not found");
        }
        return new UserInfo(user);
    }

    public User findByUserName(String username) {
        return dao.findByUserName(username);
    }

    public void activate(String username) {
        dao.activate(username);
    }

    public void disable(String username) {
        dao.disable(username);
    }

    public void update(User user) {
        dao.update(user);
    }

    public void delete(User user) {
        dao.delete(user);
    }

    public void add(User user) {
        dao.add(user);
    }

    public List<User> getUsers() {
        return dao.getAll();
    }
}
