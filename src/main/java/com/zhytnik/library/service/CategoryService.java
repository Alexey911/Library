package com.zhytnik.library.service;

import com.zhytnik.library.model.Category;

public class CategoryService extends Service<Category> {
    @Override
    protected String getExceptionMessageForObject(Category c) {
        return c.getName();
    }
}