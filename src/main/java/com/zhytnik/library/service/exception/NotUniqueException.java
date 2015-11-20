package com.zhytnik.library.service.exception;

public class NotUniqueException extends RuntimeException {
    public NotUniqueException(String msg) {
        super(msg);
    }
}