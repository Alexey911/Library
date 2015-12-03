package com.zhytnik.library.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorController {
    @RequestMapping("error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String showNotFoundPage() {
        return "error404";
    }

    @RequestMapping("error")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String showForbiddenPage() {
        return "error403";
    }
}