package com.zhytnik.library.web.command;

import com.zhytnik.library.web.ModelAndView;
import com.zhytnik.library.web.Request;

public class ErrorCommand implements Command {
    @Override
    public ModelAndView execute(Request request) {
        return new ModelAndView("/error");
    }
}