package com.zhytnik.library.web.command;

import com.zhytnik.library.web.Request;
import com.zhytnik.library.web.view.View;

import static java.util.Objects.isNull;

public abstract class SubmitCommand implements Command {
    @Override
    public View execute(Request request) {
        if (!onSubmit(request)) {
            throw new UnsupportedOperationException();
        }
        return null;
    }

    protected boolean onSubmit(Request request) {
        return !isNull(request.getParameter("submit"));
    }
}