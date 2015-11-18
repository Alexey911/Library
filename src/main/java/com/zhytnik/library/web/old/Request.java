package com.zhytnik.library.web.old;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

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
            path = ViewDispatcher.REDIRECTION;
        } else {
            path = request.getPathInfo();
        }
    }

    private boolean hasRedirectedView(HttpServletRequest request) {
        return !isNull(request.getSession().getAttribute(ViewDispatcher.REDIRECTION));
    }

    public String getPathInfo() {
        return request.getPathInfo();
    }

    public String getRequestURI() {
        return path;
    }

    public String getParameter(String name) {
        return request.getParameter(name);
    }
}