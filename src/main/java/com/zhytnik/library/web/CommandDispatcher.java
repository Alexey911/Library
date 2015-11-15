package com.zhytnik.library.web;

import com.zhytnik.library.web.command.Command;
import com.zhytnik.library.web.command.ErrorCommand;
import com.zhytnik.library.web.command.RedirectCommand;
import com.zhytnik.library.web.command.category.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Objects.isNull;

public class CommandDispatcher {
    public static final String REDIRECTION = "/redirection";

    private static final String ERROR = "/error";

    private static Logger logger;
    private Map<String, Class> controllers;

    static {
        logger = Logger.getLogger(CommandDispatcher.class);
    }

    public CommandDispatcher() {
        controllers = new HashMap<>();
        addCommand("/categories/show", ShowCategoriesCommand.class);
        addCommand("/categories/add", AddCategoryCommand.class);
        addCommand("/categories/edit", EditCategoryCommand.class);
        addCommand("/category/save", SaveCategoryCommand.class);
        addCommand("/categories/delete", DeleteCategoryCommand.class);
        addCommand("/category/update", UpdateCategoryCommand.class);
        addCommand(ERROR, ErrorCommand.class);
        addCommand(REDIRECTION, RedirectCommand.class);
    }

    private void addCommand(String actionURL, Class commandClass) {
        if (!controllers.containsKey(actionURL)) {
            controllers.put(actionURL, commandClass);
        } else {
            throw new UnsupportedOperationException(format("Action %s is already exist. " +
                    "Two controller can't map to one action.", actionURL));
        }
    }

    public Command getCommand(Request request) {
        String path = getPreparedPath(request);
        Class commandClass = controllers.get(path);
        Command command;
        if (isNull(commandClass)) {
            command = getErrorCommand(String.format("Action for %s is not found.", request.getRequestURI()));
        } else {
            try {
                command = (Command) commandClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                command = getErrorCommand(e.getMessage());
            }
        }
        return command;
    }

    private String getPreparedPath(Request request) {
        String path = request.getPathInfo();
        String[] avoidEnds = {".jsp", "/"};
        for (String avoid : avoidEnds) {
            if (path.endsWith(avoid)) {
                path = path.substring(0, path.length() - avoid.length());
            }
        }
        return path;
    }

    private Command getErrorCommand(String message) {
        logger.log(Level.INFO, message);
        Command command = null;
        try {
            command = (Command) controllers.get(ERROR).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.log(Level.INFO, e);
        }
        return command;
    }
}