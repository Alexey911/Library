package com.zhytnik.library.service;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.model.Category;

import static java.util.Objects.isNull;

public class CategoryService extends Service<Category> {
    private CategoryDao dao;

    public void setCategoryDao(CategoryDao categoryDao) {
        setGenericDao(categoryDao);
        dao = categoryDao;
    }

    @Override
    protected boolean isUniqueItem(Category category) {
        return isNull(dao.findByName(category.getName()));
    }

    @Override
    protected String getExceptionDescription(Category category) {
        return category.getName();
    }
}