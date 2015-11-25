package com.zhytnik.library.dao;

import com.zhytnik.library.domain.Category;

public interface CategoryDao extends GenericDao<Category> {
    boolean isUniqueName(String name) throws DaoException;
}