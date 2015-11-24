package com.zhytnik.library.service;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.model.Category;

public class CategoryService extends Service<Category> {
    public void setCategoryDao(CategoryDao categoryDao) {
        setDao(categoryDao);
    }

    @Override
    public Category create() {
        return new Category();
    }

    @Override
    protected boolean isUniqueItem(Category category) {
        String name = category.getName();
        CategoryDao dao = (CategoryDao) getDao();
        if (dao.isUniqueName(name)) {
            return true;
        }
        Category daoItem = dao.findById(category.getId());
        return daoItem.getName().equals(name);
    }

    @Override
    protected String getExceptionDescription(Category category) {
        return category.getName();
    }
}