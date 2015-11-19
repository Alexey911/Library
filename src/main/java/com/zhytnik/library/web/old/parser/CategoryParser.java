package com.zhytnik.library.web.old.parser;

import com.zhytnik.library.model.Category;
import com.zhytnik.library.web.old.Request;

public class CategoryParser {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

    public Category parseCategoryWithId(Request request) {
        Category category = new Category(null, null);
        return fillId(request, category);
    }

    public Category parseFullCategory(Request request) {
        Category category = fillData(request);
        return fillId(request, category);
    }

    public Category parseNewCategory(Request request) {
        return fillData(request);
    }

    private Category fillId(Request request, Category category) {
        Integer id = Integer.valueOf(request.getParameter(ID));
        category.setId(id);
        return category;
    }

    private Category fillData(Request request) {
        String name = request.getParameter(NAME);
        String desc = request.getParameter(DESCRIPTION);
        return new Category(name, desc);
    }
}