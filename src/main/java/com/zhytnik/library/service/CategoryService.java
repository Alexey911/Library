package com.zhytnik.library.service;

import com.zhytnik.library.model.Category;

public class CategoryService extends Service<Category> {
    @Override
    protected String getExceptionDescription(Category c) {
        return c.getName();
    }
}