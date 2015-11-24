package com.zhytnik.library.service;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.model.Category;

public class CategoryService extends Service<Category> {
    private CategoryDao dao;

    public void setCategoryDao(CategoryDao categoryDao) {
        setGenericDao(categoryDao);
        dao = categoryDao;
    }

    @Override
    public Category create() {
        return new Category();
    }

    @Override
    protected boolean isUniqueItem(Category category) {
        return dao.isUniqueName(category.getName());
    }

    @Override
    protected String getExceptionDescription(Category category) {
        return category.getName();
    }
}