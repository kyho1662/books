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

    @RequestMapping(value="/member/register")
    public String register() {
        return "/member/register";
    }

    @RequestMapping(value="/member/findId")
    public String findId() {
        return "/member/findId";
    }

    @RequestMapping(value="/member/resetPassword")
    public String resetPassword() {
        return "/member/resetPassword";
    }

    @RequestMapping(value="/auth/login")
    public String login() {
        return "/auth/login";
    }
}
