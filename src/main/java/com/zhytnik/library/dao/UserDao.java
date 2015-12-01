package com.zhytnik.library.dao;

import com.zhytnik.library.domain.User;

import java.util.List;

public interface UserDao {
    User findByUserName(String username);

    User findById(Integer id);

    void activate(String username);

    void disable(String username);

    void update(User user);

    void delete(Integer id);

    void add(User user);

    List<User> getAll();

    boolean isUniqueLogin(User user);
}
