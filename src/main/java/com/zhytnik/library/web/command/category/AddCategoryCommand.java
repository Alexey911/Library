package com.zhytnik.library.web.command.category;

import com.zhytnik.library.web.ModelAndView;
import com.zhytnik.library.web.Request;
import com.zhytnik.library.web.command.SubmitCommand;

public class AddCategoryCommand extends SubmitCommand {
    @Override
    public ModelAndView execute(Request request) {
        return new ModelAndView("/category/add");
    }
}