package com.zhytnik.library.dao;

import com.zhytnik.library.domain.User;

public interface UserDao extends GenericDao<User> {
    User findByUserName(String username);

    void activate(Integer id);

    void disable(Integer id);

    boolean hasUniqueLogin(User user);
}
