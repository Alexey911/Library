package com.zhytnik.library.dao;

import com.zhytnik.library.domain.Category;

public interface CategoryDao extends GenericDao<Category> {
    boolean hasUniqueName(Category category) throws DaoException;
}
