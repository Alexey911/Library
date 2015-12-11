package com.zhytnik.library.dao;

import com.zhytnik.library.domain.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByUserName(String username) throws DaoException;

    void setEnabled(Integer id, boolean isEnabled) throws DaoException;

    boolean hasUniqueLogin(User user) throws DaoException;

    List<User> getUnconfirmedUsers() throws DaoException;

    void setConfirmed(Integer id, boolean isConfirmed) throws DaoException;
}
