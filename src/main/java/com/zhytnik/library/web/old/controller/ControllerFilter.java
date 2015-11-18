package com.zhytnik.library.web.old.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.lang.String.format;

public class ControllerFilter implements Filter {
    private static Logger logger;

    static {
        logger = Logger.getLogger(ControllerFilter.class);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        logger.log(Level.INFO, uri);
        long t = System.currentTimeMillis();

        chain.doFilter(request, response);

        t = System.currentTimeMillis() - t;

        String msg = format("Executed at %4s mc.", t);
        logger.log(Level.INFO, msg);
    }

    @Override
    public void destroy() {

    }
}