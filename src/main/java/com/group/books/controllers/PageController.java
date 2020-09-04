package com.group.books.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value="/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/suggestion")
    public String suggestion() {
        return "suggestion";
    }
}
