package com.zhytnik.library.web.command.category;

import com.zhytnik.library.entity.Category;
import com.zhytnik.library.web.ModelAndView;
import com.zhytnik.library.web.Request;
import com.zhytnik.library.web.command.SubmitCommand;
import com.zhytnik.library.web.parser.CategoryParser;

public class EditCategoryCommand extends SubmitCommand {
    @Override
    public ModelAndView execute(Request request) {
        super.execute(request);

        ModelAndView modelAndView = new ModelAndView("/category/edit");

        Category category = new CategoryParser().parseFullCategory(request);
        modelAndView.addObject("category", category);

        return modelAndView;
    }
}