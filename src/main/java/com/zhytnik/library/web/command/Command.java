package com.zhytnik.library.web.command;

import com.zhytnik.library.web.Request;
import com.zhytnik.library.web.view.View;

public interface Command {
    View execute(Request request);
}