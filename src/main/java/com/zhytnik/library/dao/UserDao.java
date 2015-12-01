package com.zhytnik.library.dao;

import com.zhytnik.library.domain.User;

public interface UserDao extends GenericDao<User> {
    User findByUserName(String username);

    void activate(String username);

    void disable(String username);

    boolean hasUniqueLogin(User user);
}
