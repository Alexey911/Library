package com.zhytnik.library.dao;

import com.zhytnik.library.model.Category;

public interface CategoryDao extends GenericDao<Category> {
    Category findByName(String name) throws DaoException;
}
