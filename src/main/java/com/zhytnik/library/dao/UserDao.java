package com.zhytnik.library.dao;

import com.zhytnik.library.domain.User;

import java.util.List;
import java.util.Set;

public interface UserDao extends GenericDao<User> {
    User findByUserName(String username) throws DaoException;

    void activate(Integer id) throws DaoException;

    void disable(Integer id) throws DaoException;

    boolean hasUniqueLogin(User user) throws DaoException;

    Set<User> getNotConfirmedUsers() throws DaoException;

    void confirm(List<Integer> users) throws DaoException;
}
