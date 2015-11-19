package com.zhytnik.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage(ModelMap model) {
        model.addAttribute("info", "Library of Alexey Zhytnik =)");
        return "index";
    }
}