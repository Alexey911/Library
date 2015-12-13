package com.zhytnik.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@ControllerAdvice
public class ErrorController {
    @Autowired
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(Locale locale) {
        return getExceptionModelAndView("exception.access.denied", locale);
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleException(RuntimeException e) {
        return new ModelAndView("error", "errMsg", e);
    }

    private ModelAndView getExceptionModelAndView(String code, Locale locale) {
        String message = messageSource.getMessage(code, new String[]{}, locale);
        return new ModelAndView("error", "errMsg", message);
    }
}
