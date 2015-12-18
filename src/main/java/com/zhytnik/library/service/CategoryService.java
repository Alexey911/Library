package com.zhytnik.library.service;

import com.zhytnik.library.dao.CategoryDao;
import com.zhytnik.library.domain.Category;

import static java.util.Objects.isNull;

public class CategoryService extends AbstractService<Category> {
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
    protected boolean isUnique(Category category) {
        return ((CategoryDao) getDao()).hasUniqueName(category);
    }
}
