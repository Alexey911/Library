package com.zhytnik.library.web.controller;

import com.zhytnik.library.web.CommandDispatcher;
import com.zhytnik.library.web.Request;
import com.zhytnik.library.web.ViewDispatcher;
import com.zhytnik.library.web.command.Command;
import com.zhytnik.library.web.view.View;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private CommandDispatcher commandDispatcher;
    private ViewDispatcher viewDispatcher;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        commandDispatcher = new CommandDispatcher();
        viewDispatcher = new ViewDispatcher();
        viewDispatcher.setContext(config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Request request = new Request(req);
        View view;
        Command command;
        try {
            command = commandDispatcher.getCommand(request);
            view = command.execute(request);
        } catch (Exception e) {
            command = commandDispatcher.getErrorCommand(e.getMessage());
            view = command.execute(request);
        }
        viewDispatcher.dispatch(req, res, view);
    }
}