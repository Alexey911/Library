package com.zhytnik.library.service;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.domain.Category;

import static java.util.Objects.isNull;

public class CategoryService extends Service<Category> {
    @Override
    public Category create() {
        return new Category();
    }

    protected void prepare(Category c) {
        c.setName(c.getName().trim());
        if (!isNull(c.getDescription())) {
            c.setDescription(c.getDescription().trim());
        }
    }

    @Override
    public boolean isUnique(Category c) {
        String name = c.getName();
        CategoryDao dao = (CategoryDao) getDao();
        if (dao.isUniqueName(name)) {
            return true;
        }
        if (isNull(c.getId())) {
            return false;
        }
        Category daoItem = dao.findById(c.getId());
        return daoItem.getName().equals(name);
    }
}