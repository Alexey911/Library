package com.zhytnik.library.web.command;

import com.zhytnik.library.web.Request;
import com.zhytnik.library.web.view.SimpleView;
import com.zhytnik.library.web.view.View;

public class ErrorCommand implements Command {
    @Override
    public View execute(Request request) {
        return new SimpleView("/error");
    }
}