package com.zhytnik.library.web.command;

import com.zhytnik.library.web.ModelAndView;
import com.zhytnik.library.web.Request;

public class RedirectCommand implements Command {
    @Override
    public ModelAndView execute(Request request) {
        ModelAndView modelAndView = new ModelAndView(null);
        modelAndView.setRedirect(true);
        return modelAndView;
    }
}