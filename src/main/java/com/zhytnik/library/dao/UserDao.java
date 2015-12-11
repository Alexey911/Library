package com.zhytnik.library.dao;

import com.zhytnik.library.domain.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByUserName(String username) throws DaoException;

    boolean hasUniqueLogin(User user) throws DaoException;

    List<User> getUnconfirmedUsers() throws DaoException;
}
