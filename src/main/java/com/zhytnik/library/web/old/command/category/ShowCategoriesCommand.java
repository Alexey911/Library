package com.zhytnik.library.web.old.command.category;

import com.zhytnik.library.service.CategoryService;
import com.zhytnik.library.web.old.ModelAndView;
import com.zhytnik.library.web.old.Request;
import com.zhytnik.library.web.old.command.Command;
import com.zhytnik.library.web.old.view.View;

import static com.zhytnik.library.tools.Utils.getContext;

public class ShowCategoriesCommand implements Command {
    @Override
    public View execute(Request request) {
        ModelAndView modelAndView = new ModelAndView("/categories/show");

        CategoryService service = (CategoryService) getContext().getBean("categoryService");

        modelAndView.addObject("categories", service.getAll());

        return modelAndView;
    }
}