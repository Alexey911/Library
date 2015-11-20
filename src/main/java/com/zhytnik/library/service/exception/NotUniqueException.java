package com.zhytnik.library.service.exception;

public class NotUniqueException extends RuntimeException {
    private String description;

    public NotUniqueException(String msg, String description) {
        super(msg);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}