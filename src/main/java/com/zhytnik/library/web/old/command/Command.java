package com.zhytnik.library.web.old.command;

import com.zhytnik.library.web.old.Request;
import com.zhytnik.library.web.old.view.View;

public interface Command {
    View execute(Request request);
}