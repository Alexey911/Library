package com.zhytnik.library.web.command;

import com.zhytnik.library.web.ModelAndView;
import com.zhytnik.library.web.Request;

import static java.util.Objects.isNull;

public abstract class SubmitCommand implements Command {
    @Override
    public ModelAndView execute(Request request) {
        if (!onSubmit(request)) {
            throw new UnsupportedOperationException();
        }
        return null;
    }

    protected boolean onSubmit(Request request) {
        return !isNull(request.getParameter("submit"));
    }
}