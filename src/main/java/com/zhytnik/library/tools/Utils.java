package com.zhytnik.library.tools;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//this class needs checking of efficiency of using
public class Utils {
    private static ExecutorService service;

    static {
        service = Executors.newSingleThreadExecutor();
    }

    public static void asynchronousLog(Logger logger, Level level, Object msg) {
        service.submit(() -> logger.log(level, msg));
    }
}