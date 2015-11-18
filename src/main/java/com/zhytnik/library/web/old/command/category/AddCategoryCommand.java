package com.zhytnik.library.web.old.command.category;

import com.zhytnik.library.web.old.Request;
import com.zhytnik.library.web.old.command.SubmitCommand;
import com.zhytnik.library.web.old.view.SimpleView;
import com.zhytnik.library.web.old.view.View;

public class AddCategoryCommand extends SubmitCommand {
    @Override
    public View execute(Request request) {
        return new SimpleView("/category/add");
    }
}