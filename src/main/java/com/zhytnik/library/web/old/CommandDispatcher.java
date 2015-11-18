package com.zhytnik.library.web.old;

import com.zhytnik.library.web.old.command.Command;
import com.zhytnik.library.web.old.command.ErrorCommand;
import com.zhytnik.library.web.old.command.RedirectCommand;
import com.zhytnik.library.web.old.command.category.*;
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

    static {
        logger = Logger.getLogger(CommandDispatcher.class);
    }

    private Map<String, Class> controllers;

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
        Class commandClass = controllers.get(request.getPathInfo());
        Command command;
        if (isNull(commandClass)) {
            command = getErrorCommand(format("Action for %s is not found.", request.getRequestURI()));
        } else {
            try {
                command = (Command) commandClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                command = getErrorCommand(e.getMessage());
            }
        }
        return command;
    }

    public Command getErrorCommand(String message) {
        logger.log(Level.ERROR, message);
        Command command = null;
        try {
            command = (Command) controllers.get(ERROR).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.log(Level.ERROR, e);
        }
        return command;
    }
}