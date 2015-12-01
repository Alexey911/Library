package com.zhytnik.library.dao;

import com.zhytnik.library.domain.User;

import java.util.Set;

public interface UserDao extends GenericDao<User> {
    User findByUserName(String username);

    void activate(String username);

    void disable(String username);

    Set<User> getAll();

    boolean isUniqueLogin(User user);
}
