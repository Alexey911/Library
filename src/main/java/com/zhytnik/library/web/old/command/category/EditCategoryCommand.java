package com.zhytnik.library.web.old.command.category;

import com.zhytnik.library.model.Category;
import com.zhytnik.library.web.old.ModelAndView;
import com.zhytnik.library.web.old.Request;
import com.zhytnik.library.web.old.command.SubmitCommand;
import com.zhytnik.library.web.old.parser.CategoryParser;
import com.zhytnik.library.web.old.view.View;

public class EditCategoryCommand extends SubmitCommand {
    @Override
    public View execute(Request request) {
        super.execute(request);

        ModelAndView modelAndView = new ModelAndView("/category/edit");

        Category category = new CategoryParser().parseFullCategory(request);
        modelAndView.addObject("category", category);

        return modelAndView;
    }
}