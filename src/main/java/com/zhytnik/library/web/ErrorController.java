package com.zhytnik.library.web;

import com.zhytnik.library.service.exception.DeleteAssociatedObjectException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleException(RuntimeException e) {
        return new ModelAndView("error", "errMsg", e);
    }

    @ExceptionHandler(DeleteAssociatedObjectException.class)
    public ModelAndView handleDeleteFail(DeleteAssociatedObjectException e) {
        return new ModelAndView("error", "errMsg", e);
    }
}
