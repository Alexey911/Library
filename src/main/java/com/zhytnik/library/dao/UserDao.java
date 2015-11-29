package com.zhytnik.library.dao;

import com.zhytnik.library.domain.User;

import java.util.List;

public interface UserDao {
    User findByUserName(String username);

    void activate(String username);

    void disable(String username);

    void update(User user);

    void delete(User user);

    void add(User user);

    List<User> getUsers();
}
