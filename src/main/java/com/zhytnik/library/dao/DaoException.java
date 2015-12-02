package com.zhytnik.library.dao;

public class DaoException extends RuntimeException {
    public DaoException() {
        super();
    }

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(Exception cause) {
        super(cause);
    }

    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DaoException(String msg, Throwable cause, boolean suppressEnable, boolean withStackTrace) {
        super(msg, cause, suppressEnable, withStackTrace);
    }
}
