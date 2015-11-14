package com.zhytnik.library.web.command;

import com.zhytnik.library.web.ModelAndView;
import com.zhytnik.library.web.Request;

public interface Command {
    ModelAndView execute(Request request);
}