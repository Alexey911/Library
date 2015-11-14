package com.zhytnik.library.service.exception;

public class NotUniqueNameException extends RuntimeException {
    public NotUniqueNameException(String msg) {
        super(msg);
    }
}