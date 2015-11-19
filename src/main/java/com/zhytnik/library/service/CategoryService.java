package com.zhytnik.library.service;

import com.zhytnik.library.model.Category;

import static com.google.common.base.Strings.isNullOrEmpty;

public class CategoryService extends Service<Category> {
    public CategoryService() {
        super();
    }

    @Override
    public void validateFullness(Category c) {
        if (isNullOrEmpty(c.getName())) {
            throwIllegalArgException("Set name of category: " + c);
        }
        String format = "%s length must be less than or equals to %d";
        if (c.getName().length() > 50) {
            throwIllegalArgException(String.format(format, "Name", 50));
        }
        if (c.getDescription().length() > 150) {
            throwIllegalArgException(String.format(format, "Description", 15));
        }
    }
}