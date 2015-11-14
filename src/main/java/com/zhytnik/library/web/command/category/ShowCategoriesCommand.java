package com.zhytnik.library.web.command.category;

import com.zhytnik.library.service.ServiceFactory;
import com.zhytnik.library.web.ModelAndView;
import com.zhytnik.library.web.Request;
import com.zhytnik.library.web.command.Command;

import java.util.Set;

public class ShowCategoriesCommand implements Command {
    @Override
    public ModelAndView execute(Request request) {
        ModelAndView modelAndView = new ModelAndView("/categories/show");

        Set categories = ServiceFactory.getInstance().getCategoryService().getAll();
        modelAndView.addObject("categories", categories);

        return modelAndView;
    }
}