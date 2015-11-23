package com.zhytnik.library.dao;

import com.zhytnik.library.model.Category;

import java.util.Set;

public interface CategoryDao extends GenericDao<Category> {
    Set<Category> findByName(String name) throws DaoException;
}