package com.zhytnik.library.web.old.command;

import com.zhytnik.library.web.old.Request;
import com.zhytnik.library.web.old.view.SimpleView;
import com.zhytnik.library.web.old.view.View;

public class ErrorCommand implements Command {
    @Override
    public View execute(Request request) {
        return new SimpleView("/error");
    }
}