package com.zhytnik.library.tools;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Utils {
    private static ExecutorService service;

    private static ApplicationContext context;

    static {
        service = Executors.newSingleThreadExecutor();
        context = new ClassPathXmlApplicationContext("Beans.xml");
    }

    //this method needs checking of efficiency of using
    public static void asynchronousLog(Logger logger, Level level, Object msg) {
        service.submit(() -> logger.log(level, msg));
    }

    public static ApplicationContext getContext() {
        return context;
    }
}