package com.zhytnik.library.web.command.category;

import com.zhytnik.library.web.Request;
import com.zhytnik.library.web.command.SubmitCommand;
import com.zhytnik.library.web.view.SimpleView;
import com.zhytnik.library.web.view.View;

public class AddCategoryCommand extends SubmitCommand {
    @Override
    public View execute(Request request) {
        return new SimpleView("/category/add");
    }
}