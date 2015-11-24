package com.zhytnik.library.service;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.model.Category;

import static java.util.Objects.isNull;

public class CategoryService extends Service<Category> {
    public void setCategoryDao(CategoryDao categoryDao) {
        setDao(categoryDao);
    }

    @Override
    public Category create() {
        return new Category();
    }

    @Override
    public boolean isUnique(Category category) {
        String name = category.getName();
        CategoryDao dao = (CategoryDao) getDao();
        if (dao.isUniqueName(name)) {
            return true;
        }
        if (isNull(category.getId())) {
            return false;
        }
        Category daoItem = dao.findById(category.getId());
        return daoItem.getName().equals(name);
    }
}