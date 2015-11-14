package com.zhytnik.library.service.exception;

public class NotFoundItemException extends RuntimeException {
    public NotFoundItemException(String msg) {
        super(msg);
    }
}