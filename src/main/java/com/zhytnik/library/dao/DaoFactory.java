package com.zhytnik.library.dao;

public interface DaoFactory {
    GenericDao getDao(Class entityClass) throws DaoException;
}