package com.zhytnik.library.web;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

import static com.zhytnik.library.web.CommandDispatcher.REDIRECTION;
import static java.util.Objects.isNull;

public class Request {
    private HttpServletRequest request;
    private String path;

    public Request(HttpServletRequest request) {
        this.request = request;
        try {
            this.request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ignored) {

        }
        if (hasRedirectedView(request)) {
            path = REDIRECTION;
        } else {
            path = request.getPathInfo();
        }
    }

    private boolean hasRedirectedView(HttpServletRequest request) {
        return !isNull(request.getSession().getAttribute(ViewDispatcher.REDIRECTED_VIEW));
    }

    public String getPathInfo() {
        return path;
    }

    public String getRequestURI() {
        return request.getRequestURI();
    }

    public String getParameter(String name) {
        return request.getParameter(name);
    }
}